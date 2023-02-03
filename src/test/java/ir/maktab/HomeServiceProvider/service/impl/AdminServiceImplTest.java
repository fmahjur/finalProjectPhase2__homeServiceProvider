package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Credit;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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

    @BeforeAll
    static void setup(@Autowired DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("AdminServiceData.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    GetImage image = new GetImage();

    BaseService baseService = new BaseService("service1");
    Expert expert = new Expert("reihan", "mahjour", "reihaneh763@gmail.com", "Rm@123456", new Credit(1000L), image.getImage());


    @Test
    @Order(1)
    void testAddNewService() {
        BaseService saveBaseService = adminService.addNewService(baseService);
        assertNotNull(saveBaseService.getId());
    }

    @Test
    @Order(2)
    void testAddNewSubService() {
        BaseService findBaseService = baseServiceService.findById(3L);
        SubService subService = new SubService(findBaseService, "subService3", "description", 100.0);
        baseService.getSubServiceList().add(subService);
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
        Expert findExpert = expertService.findById(1L);
        SubService subService = subServiceService.findById(3L);
        adminService.allocationServiceToExpert(findExpert, subService);
        assertThat(expertService.findById(1L).getSubServices().size()).isEqualTo(1);
    }

    @Test
    @Order(5)
    void testRemoveExpertFromService() {
        Expert findExpert = expertService.findById(1L);
        SubService subService = subServiceService.findById(3L);
        adminService.removeExpertFromService(findExpert, subService);
        assertThat(expertService.findById(1L).getSubServices().size()).isEqualTo(0);
    }

    @Test
    void showAllService() {

    }

    @Test
    void showSubServices() {
    }

    @Test
    void showAllCustomer() {
    }

    @Test
    void showAllExpert() {
    }

    @Test
    void showAllNewExpert() {
    }

    @Test
    void showAllConfirmedExpert() {
    }
}