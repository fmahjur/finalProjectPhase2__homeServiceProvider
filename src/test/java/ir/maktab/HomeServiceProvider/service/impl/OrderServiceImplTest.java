package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.Utils.DateUtil;
import ir.maktab.HomeServiceProvider.data.enums.City;
import ir.maktab.HomeServiceProvider.data.model.*;
import ir.maktab.HomeServiceProvider.data.repository.AddressRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceImplTest {
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private BaseServiceServiceImpl baseServiceService;
    @Autowired
    private SubServiceServiceImpl subServiceService;
    @Autowired
    private ExpertServiceImpl expertService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OfferServiceImpl offerService;
    @Autowired
    private AddressRepository addressRepository;

    GetImage image = new GetImage();

    Customer customer = new Customer("fatemeh", "mahjour", "fmahjour@gmail.com", "FMAh@1234568", new Credit(1500L));
    Customer customer1 = new Customer("mahin", "mahjour", "mahnaz@gmail.com", "mAhin@1234568", new Credit(3000L));
    BaseService baseService = new BaseService(1L, "service1");
    SubService subService = new SubService(1L, baseService, "subService1", "description", 100.2);
    Expert expert = new Expert(1L, "fatemeh", "mahjour", "fmahjour@gmail.com", "Fm@123456", new Credit(1000L), image.getImage());
    Address address = new Address(City.TEHRAN, "satarkhan");
    Address address1 =  new Address(City.TEHRAN, "Enghelab");
    Orders order = new Orders("CO123", customer, subService, "description", 200L,
            DateUtil.asDate(LocalDateTime.of(2023, 3, 5, 18, 30)),
            3,address);
    Orders order1 = new Orders("CO234", customer1, subService, "description", 300L,
            DateUtil.asDate(LocalDateTime.of(2023, 3, 6, 18, 30)),
            3, address1);
    Offer offer = new Offer(1L, expert, order, 220L,
            DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 13, 30)),
            4);

    @Test
    @Order(1)
    void testAddNewOrder() {
        baseServiceService.add(baseService);
        subServiceService.add(subService);
        addressRepository.save(address);
        customerService.add(customer);
        Orders saveOrder = orderService.add(order);
        order.setId(saveOrder.getId());
        assertNotNull(saveOrder.getId());
    }

    @Test
    @Order(2)
    void testRemoveOrder() {
        Orders removeOrder = orderService.findById(1L);
        orderService.remove(removeOrder);
        Orders afterRemoveOrder = orderService.findById(1L);
        assertThat(removeOrder.isDeleted()).isEqualTo(true);
    }

    @Test
    @Order(3)
    void testUpdateOrder() {
        Orders updateOrder = orderService.findById(1L);
        updateOrder.setDeleted(false);
        orderService.update(updateOrder);
        Orders afterUpdateOrder = orderService.findById(1L);
        assertThat(afterUpdateOrder.isDeleted()).isEqualTo(false);
    }

    @Test
    @Order(4)
    void testFindById() {
        Orders findOrder = orderService.findById(1L);
        assertNotNull(findOrder);
    }

    @Test
    void testSelectAll() {
        List<Orders> orders = orderService.selectAll();
        assertThat(orders.size()).isEqualTo(1);
    }

    @Test
    void testSelectAllAvailableService() {
        List<Orders> orders = orderService.selectAllAvailableService();
        assertThat(orders.size()).isEqualTo(1);
    }

    @Test
    void testSelectAllCustomersOrders() {
        Customer saveCustomer = customerService.findById(1L);
        List<Orders> orders = orderService.selectAllCustomersOrders(saveCustomer);
        assertThat(orders.size()).isEqualTo(1);
    }
}