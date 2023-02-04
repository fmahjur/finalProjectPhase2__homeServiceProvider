package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.*;
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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminServiceImplTest {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private BaseServiceServiceImpl baseServiceService;
    @Autowired
    private SubServiceServiceImpl subServiceService;
    @Autowired
    private ExpertServiceImpl expertService;
    @Autowired
    private CustomerServiceImpl customerService;

    GetImage image = new GetImage();

    BaseService baseService = new BaseService("service");
    BaseService baseService1 = new BaseService("service1");
    BaseService baseService2 = new BaseService("service2");
    SubService subService = new SubService(baseService2, "subService", "description", 100.2);
    SubService subService1 = new SubService(2L, baseService1, "subService1", "description", 200.2);
    Expert expert = new Expert(5L, "reihan", "mahjour", "reihaneh763@gmail.com", "Rm@123456", new Credit(1000L), image.getImage());


    @Test
    @Order(1)
    void testAddNewService() {
        BaseService saveBaseService = adminService.addNewService(baseService);
        baseService.setId(saveBaseService.getId());
        assertNotNull(saveBaseService.getId());
    }

    @Test
    @Order(2)
    void testAddNewSubService() {
        BaseService saveBaseService = baseServiceService.add(baseService2);
        subService.setBaseService(saveBaseService);
        SubService saveSubService = adminService.addNewSubService(subService);
        assertNotNull(saveSubService.getId());
    }

    @Test
    @Order(3)
    void testConfirmExpert() {
        Expert saveExpert = expertService.add(expert);
        adminService.confirmExpert(saveExpert);
        Expert confirmExpert = expertService.findById(saveExpert.getId());
        assertThat(confirmExpert.getExpertStatus()).isEqualTo(ExpertStatus.ACCEPTED);
    }

    @Test
    @Order(4)
    void testAllocationServiceToExpert() {
        baseServiceService.add(baseService1);
        subServiceService.add(subService1);
        Expert findExpert = expertService.findById(1L);
        SubService subService = subServiceService.findById(1L);
        adminService.allocationServiceToExpert(findExpert, subService);
        assertThat(expertService.findById(1L).getSubServices().size()).isEqualTo(1);
    }

    @Test
    @Order(5)
    void testRemoveExpertFromService() {
        Expert findExpert = expertService.findById(1L);
        SubService subService = subServiceService.findById(1L);
        adminService.removeExpertFromService(findExpert, subService);
        assertThat(expertService.findById(1L).getSubServices().size()).isEqualTo(0);
    }

    @Test
    @Order(6)
    void testShowAllService() {
        assertThat(baseServiceService.selectAll().size()).isEqualTo(3);
    }

    @Test
    @Order(7)
    void testShowSubServicesByBaseService() {
        BaseService baseServiceForFind = baseServiceService.findById(2L);
        assertThat(subServiceService.getSubServicesByService(baseServiceForFind).size()).isEqualTo(1);
    }

    @Test
    void testShowAllCustomer() {
        List<Customer> customerList = customerService.selectAllAvailableService();
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(0);
    }

    @Test
    void yesyShowAllExpert() {
        List<Expert> expertList = expertService.selectAll();
        assertThat(expertList).isNotNull();
        assertThat(expertList.size()).isEqualTo(1);
    }

    @Test
    void testShowAllNewExpert() {
        List<Expert> expertList = expertService.selectExpertByExpertStatus(ExpertStatus.NEW);
        assertThat(expertList).isNotNull();
        assertThat(expertList.size()).isEqualTo(0);
    }

    @Test
    void testShowAllConfirmedExpert() {
        List<Expert> expertList = expertService.selectExpertByExpertStatus(ExpertStatus.ACCEPTED);
        assertThat(expertList).isNotNull();
        assertThat(expertList.size()).isEqualTo(1);
    }
}