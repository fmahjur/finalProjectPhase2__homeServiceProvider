package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.HomeServiceProvider.data.model.Credit;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Orders;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
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
    private OrderServiceImpl orderService;
    @Autowired
    private OfferServiceImpl offerService;

//    @BeforeAll
//    static void setup(@Autowired DataSource dataSource) {
//        try (Connection connection = dataSource.getConnection()) {
//            ScriptUtils.executeSqlScript(connection, new ClassPathResource("CustomerServiceData.sql"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    Customer customer = new Customer("fatemeh", "mahjour", "fmahjour@gmail.com", "FMAh@1234568", new Credit(1500L));
    Customer customer1 = new Customer("mahin", "mahjour", "mahnaz@gmail.com", "mAhin@1234568", new Credit(3000L));

    @Test
    void testAddNewCustomer() {
        Customer saveCustomer = customerService.add(customer);
        assertNotNull(saveCustomer.getId());
    }

    @Test
    void testSoftDeleteCustomer() {
        Customer saveCustomer = customerService.add(customer1);
        customerService.remove(customer1);
        Customer optionalCustomer = customerService.findById(saveCustomer.getId());
        assertThat(optionalCustomer.isDeleted()).isEqualTo(true);
    }

    @Test
    void testUpdateCustomer() {
        Customer findCustomer = customerService.findById(1L);
        findCustomer.setFirstname("updateFirstName");
        Customer updateCustomer = customerService.update(findCustomer);
        assertThat(updateCustomer.getFirstname()).isEqualTo("updateFirstName");
    }

    @Test
    void testFindById() {
        assertNotNull(customerService.findById(1L));
    }

    @Test
    void testSelectAll() {
        assertThat(customerService.selectAll().size()).isEqualTo(3);
    }

    @Test
    void selectAllAvailableService() {
        List<Customer> customerList = customerService.selectAllAvailableService();
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(2);
    }

    @Test
    void login() {
    }

    @Test
    void teatCustomerChangePassword() {
        Customer findCustomer = customerService.findById(1L);
        Customer customerChangePassword = customerService.changePassword(findCustomer, "RMahjour@123", "RMahjour@123");
        assertThat(customerChangePassword.getPassword()).isEqualTo("RMahjour@123");
    }

    @Test
    void teatCustomerChangePasswordWhenNewPasswordInValid() {
        Customer findCustomer = customerService.findById(1L);
        assertThrows(ValidationException.class, () -> customerService.changePassword(findCustomer, "123456789", "84563214"));
    }

    @Test
    @Order(1)
    void testChoseAnExpertForOrder() {
        Orders newOrder = orderService.findById(2L);
        Offer newOffer = offerService.findById(1L);
        customerService.choseAnExpertForOrder(newOrder, newOffer);
        assertThat(orderService.findById(1L).getOrderStatus()).isEqualTo("WAITING_FOR_EXPERT_TO_COME");
    }

    @Test
    @Order(2)
    void testChangeOrderStatusToStarted() {
        Orders startOrder = orderService.findById(2L);
        startOrder.setOrderStatus(OrderStatus.STARTED);
        assertThat(orderService.findById(1L).getOrderStatus()).isEqualTo("STARTED");
    }

    @Test
    @Order(3)
    void testChangeOrderStatusToDone() {
        Orders startOrder = orderService.findById(2L);
        startOrder.setOrderStatus(OrderStatus.DONE);
        assertThat(orderService.findById(1L).getOrderStatus()).isEqualTo("DONE");
    }

    @Test
    void addNewComment() {
    }
}