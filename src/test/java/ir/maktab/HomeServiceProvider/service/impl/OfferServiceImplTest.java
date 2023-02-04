package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.Utils.DateUtil;
import ir.maktab.HomeServiceProvider.data.enums.City;
import ir.maktab.HomeServiceProvider.data.model.*;
import ir.maktab.HomeServiceProvider.data.repository.AddressRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OfferServiceImplTest {
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
    BaseService baseService = new BaseService(1L, "service1");
    SubService subService = new SubService(1L, baseService, "subService1", "description", 100.2);
    Expert expert = new Expert(1L, "fatemeh", "mahjour", "fmahjour@gmail.com", "Fm@123456", new Credit(1000L), image.getImage());
    Address address = new Address(City.TEHRAN, "satarkhan");
    Orders order = new Orders(1L, "CO123", customer, subService, "description", 200L,
            DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 12, 30)),
            3,address);
    Offer offer = new Offer(1L, expert, order , 220L,
            DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 13, 30)),
            4);
    @Test
    @Order(1)
    void add() {
        expertService.add(expert);
        baseServiceService.add(baseService);
        subServiceService.add(subService);
        customerService.add(customer);
        addressRepository.save(address);
        orderService.add(order);
        Offer saveOffer = offerService.add(offer);
        offer.setId(saveOffer.getId());
        assertNotNull(saveOffer.getId());
    }

    @Test
    @Order(2)
    void remove() {
        Offer findOffer = offerService.findById(1L);
        offerService.remove(findOffer);
        Offer removeOffer = offerService.findById(1L);
        assertThat(removeOffer.isDeleted()).isEqualTo(true);
    }

    @Test
    @Order(3)
    void update() {
        Offer updateOffer = offerService.findById(1L);
        updateOffer.setDeleted(false);
        offerService.update(updateOffer);
        Offer afterUpdateOffer = offerService.findById(1L);
        assertThat(afterUpdateOffer.isDeleted()).isEqualTo(false);
    }

    @Test
    @Order(4)
    void findById() {
        Offer findOffer = offerService.findById(1L);
        assertNotNull(findOffer);
    }

    @Test
    @Order(5)
    void selectAll() {
        List<Offer> offerList = offerService.selectAll();
        assertThat(offerList.size()).isEqualTo(1);
    }

    @Test
    @Order(6)
    void selectAllAvailableService() {
        List<Offer> offerList = offerService.selectAllAvailableService();
        assertThat(offerList.size()).isEqualTo(1);
    }

    @Test
    @Order(7)
    void selectAllByOrder() {
        Orders order = orderService.findById(1L);
        List<Offer> offerList = offerService.selectAllByOrder(order);
        assertThat(offerList.size()).isEqualTo(1);
    }

    @Test
    @Order(8)
    void selectAllByExpert() {
        Expert expert = expertService.findById(1L);
        List<Offer> offerList = offerService.selectAllByExpert(expert);
        assertThat(offerList.size()).isEqualTo(1);
    }
}