package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.Utils.DateUtil;
import ir.maktab.HomeServiceProvider.data.enums.City;
import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.*;
import ir.maktab.HomeServiceProvider.data.repository.AddressRepository;
import ir.maktab.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExpertServiceImplTest {
    @Autowired
    private ExpertServiceImpl expertService;
    @Autowired
    private SubServiceServiceImpl subServiceService;
    @Autowired
    private CommentServiceImp commentService;
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private BaseServiceServiceImpl baseServiceService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OfferServiceImpl offerService;
    @Autowired
    private AddressRepository addressRepository;

    GetImage image = new GetImage();

    Expert expert = new Expert("reihan", "mahjour", "reihaneh763@gmail.com", "Rm@123456", new Credit(1000L), image.getImage());
    Expert expert1 = new Expert("hamid", "mahjour", "hamid@gmail.com", "Hm@123456", new Credit(1000L), image.getImage());
    Expert expert2 = new Expert(3L, "fatemeh", "mahjour", "fmahjour@gmail.com", "Fm@123456", new Credit(1000L), image.getImage());
    Customer customer = new Customer("hamid", "mahjour", "hmahjour@gmail.com", "Hm@1234568", new Credit(1500L));
    BaseService baseService = new BaseService(1L, "service1");
    SubService subService = new SubService(1L, baseService, "subService1", "description", 100.2);
    Address address = new Address(City.TEHRAN, "satarkhan");
    Orders order = new Orders(1L, "CO123", customer, subService, "description", 200L,
            DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 12, 30)),
            3, address);
    Offer offer = new Offer(1L, expert2, order, 220L,
            DateUtil.asDate(LocalDateTime.of(2023, 2, 5, 13, 30)),
            4);
    Comment comment = new Comment(1L, "Comment1", 5.0);
    @Test
    @Order(1)
    void testAddNewExpert() {
        Expert saveExpert = expertService.add(expert);
        expert.setId(saveExpert.getId());
        assertNotNull(saveExpert.getId());
    }

    @Test
    @Order(2)
    void testAddNewExpertWhenExpertIsExist() {
        assertThrows(ValidationException.class, () -> expertService.add(expert));
    }

    @Test
    @Order(3)
    void testSoftDeleteExpert() {
        Expert expertAfterSave = expertService.add(expert1);
        expertService.remove(expert1);
        Optional<Expert> optionalExpert = expertRepository.findById(expertAfterSave.getId());
        assertThat(optionalExpert.get().isDeleted()).isEqualTo(true);
    }

    @Test
    @Order(4)
    void testUpdateExpert() {
        Expert findExpert = expertService.findById(1L);
        findExpert.setFirstname("updateFirstName");
        Expert updateExpert = expertService.update(findExpert);
        assertThat(updateExpert.getFirstname()).isEqualTo("updateFirstName");
    }

    @Test
    @Order(5)
    void testUpdateExpertSubService() {
        baseServiceService.add(baseService);
        subServiceService.add(subService);
        Expert findExpert = expertService.findById(1L);
        SubService subService = subServiceService.findById(1L);
        expertService.updateExpertSubService(subService, findExpert);
        assertThat(expertService.findById(1L).getSubServices().size()).isEqualTo(1);
    }

    @Test
    @Order(6)
    void testReceivedNewCommentForExpert() {
        expertService.add(expert2);
        commentService.add(comment);
        Expert findExpert = expertService.findById(3L);
        Comment comment = commentService.findById(1L);
        expertService.receivedNewComment(comment, findExpert);
        assertThat(expertService.findById(3L).getComments().size()).isEqualTo(1);
    }

    @Test
    @Order(7)
    void testSelectAllAvailableExpert() {
        List<Expert> expertList = expertService.selectAll();
        assertThat(expertList).isNotNull();
        assertThat(expertList.size()).isEqualTo(3);
    }

    @Test
    void teatExpertChangePassword() {
        Expert findExpert = expertService.findById(1L);
        Expert expertChangePassword = expertService.changePassword(findExpert, "RMahjour@123", "RMahjour@123");
        assertThat(expertChangePassword.getPassword()).isEqualTo("RMahjour@123");
    }

    @Test
    void teatExpertChangePasswordWhenNewPasswordInValid() {
        Expert findExpert = expertService.findById(1L);
        assertThrows(ValidationException.class, () -> expertService.changePassword(findExpert, "123456789", "84563214"));
    }

    @Test
    void testSelectExpertByExpertStatus() {
        List<Expert> expertList = expertService.selectExpertByExpertStatus(ExpertStatus.NEW);
        assertThat(expertList).isNotNull();
        assertThat(expertList.size()).isEqualTo(3);
    }

    @Test
    void testSubmitAnOffer() {
        addressRepository.save(address);
        customerService.add(customer);
        orderService.add(order);
        offerService.add(offer);
        expertService.submitAnOffer(offer, order);
    }

    @Test
    void testGetPersonalPhoto() throws IOException {
        byte[] personalPhoto = expertService.getImage(1L);
        FileOutputStream image = new FileOutputStream("C:\\Users\\paage\\OneDrive\\Documents\\reihaneh\\3333.jpg");
        image.write(personalPhoto);
    }
}