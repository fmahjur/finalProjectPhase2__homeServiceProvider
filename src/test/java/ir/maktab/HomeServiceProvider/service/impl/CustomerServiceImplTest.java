package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.Utils.DateUtil;
import ir.maktab.HomeServiceProvider.data.enums.City;
import ir.maktab.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.HomeServiceProvider.data.model.*;
import ir.maktab.HomeServiceProvider.data.repository.AddressRepository;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceImplTest {
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
    private CommentServiceImp commentService;
    @Autowired
    private AddressRepository addressRepository;

    GetImage image = new GetImage();

    Customer customer = new Customer("fatemeh", "mahjour", "fmahjour@gmail.com", "FMAh@1234568", new Credit(1500L));
    Customer customer1 = new Customer("mahin", "mahjour", "mahnaz@gmail.com", "mAhin@1234568", new Credit(3000L));
    Customer customer2 = new Customer("hamid", "mahjour", "hmahjour@gmail.com", "Hm@1234568", new Credit(1500L));
    BaseService baseService = new BaseService(1L, "service1");
    SubService subService = new SubService(1L, baseService, "subService1", "description", 100.2);
    Expert expert = new Expert(1L, "fatemeh", "mahjour", "fmahjour@gmail.com", "Fm@123456", new Credit(1000L), image.getImage());
    Address address = new Address(City.TEHRAN, "satarkhan");
    Orders order = new Orders(1L, "CO123", customer2, subService, "description", 200L,
            DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 12, 30)),
            3,address);
    Offer offer = new Offer(1L, expert, order , 220L,
            DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 13, 30)),
            4);

    Comment comment = new Comment(1L, "Comment1", 5.0, expert);
    @Test
    @Order(1)
    void testAddNewCustomer() {
        Customer saveCustomer = customerService.add(customer);
        assertNotNull(saveCustomer.getId());
    }

    @Test
    @Order(2)
    void testSoftDeleteCustomer() {
        customerService.add(customer1);
        customerService.remove(customer1);
        Customer optionalCustomer = customerService.findById(2L);
        assertThat(optionalCustomer.isDeleted()).isEqualTo(true);
    }

    @Test
    @Order(3)
    void testUpdateCustomer() {
        Customer findCustomer = customerService.findById(1L);
        findCustomer.setFirstname("updateFirstName");
        Customer updateCustomer = customerService.update(findCustomer);
        assertThat(updateCustomer.getFirstname()).isEqualTo("updateFirstName");
    }

    @Test
    @Order(4)
    void testFindById() {
        assertNotNull(customerService.findById(1L));
    }

    @Test
    @Order(5)
    void testSelectAll() {
        assertThat(customerService.selectAll().size()).isEqualTo(2);
    }

    @Test
    @Order(6)
    void selectAllAvailableService() {
        List<Customer> customerList = customerService.selectAllAvailableService();
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(1);
    }

    @Test
    @Order(7)
    void teatCustomerChangePassword() {
        Customer findCustomer = customerService.findById(1L);
        Customer customerChangePassword = customerService.changePassword(findCustomer, "RMahjour@123", "RMahjour@123");
        assertThat(customerChangePassword.getPassword()).isEqualTo("RMahjour@123");
    }

    @Test
    @Order(8)
    void teatCustomerChangePasswordWhenNewPasswordInValid() {
        Customer findCustomer = customerService.findById(1L);
        assertThrows(ValidationException.class, () -> customerService.changePassword(findCustomer, "123456789", "84563214"));
    }

    @Test
    @Order(9)
    void testChoseAnExpertForOrder() {
        baseServiceService.add(baseService);
        subServiceService.add(subService);
        addressRepository.save(address);
        customerService.add(customer2);
        expertService.add(expert);
        orderService.add(order);
        offerService.add(offer);
        orderService.receivedNewOffer(offer, order);
        customerService.choseAnExpertForOrder(order, offer);
        assertThat(orderService.findById(1L).getOrderStatus()).isEqualTo(OrderStatus.WAITING_FOR_EXPERT_TO_COME);
    }

    @Test
    @Order(10)
    void testChangeOrderStatusToStarted() {
        Orders startOrder = orderService.findById(1L);
        customerService.changeOrderStatusToStarted(startOrder);
        assertThat(orderService.findById(1L).getOrderStatus()).isEqualTo(OrderStatus.STARTED);
    }

    @Test
    @Order(11)
    void testChangeOrderStatusToDone() {
        Orders doneOrder = orderService.findById(1L);
        customerService.changeOrderStatusToDone(doneOrder);
        assertThat(orderService.findById(1L).getOrderStatus()).isEqualTo(OrderStatus.DONE);
    }

    @Order(12)
    @Test
    void addNewComment() {
        commentService.add(comment);
        customerService.addNewComment(comment, expert);
    }
}