package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Comment;
import ir.maktab.HomeServiceProvider.data.model.Credit;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

    @BeforeAll
    static void setup(@Autowired DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("ExpertServiceData.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    GetImage image = new GetImage();

    Expert expert = new Expert("reihan", "mahjour", "reihaneh763@gmail.com", "Rm@123456", new Credit(1000L), image.getImage());
    Expert expert1 = new Expert("hamid", "mahjour", "hamid@gmail.com", "Hm@123456", new Credit(1000L), image.getImage());

    @Order(1)
    @Test
    void testAddNewExpert() {
        Expert saveExpert = expertService.add(expert);
        expert.setId(saveExpert.getId());
        assertNotNull(saveExpert.getId());
    }

    @Order(2)
    @Test
    void testAddNewExpertWhenExpertIsExist() {
        assertThrows(ValidationException.class, () -> expertService.add(expert));
    }

    @Test
    void testSoftDeleteExpert() {
        Expert expertAfterSave = expertService.add(expert1);
        expertService.remove(expert1);
        Optional<Expert> optionalExpert = expertRepository.findById(expertAfterSave.getId());
        assertThat(optionalExpert.get().isDeleted()).isEqualTo(true);
    }

    @Test
    void testUpdateExpert() {
        Expert findExpert = expertService.findById(1L);
        findExpert.setFirstname("updateFirstName");
        Expert updateExpert = expertService.update(findExpert);
        assertThat(updateExpert.getFirstname()).isEqualTo("updateFirstName");
    }

    @Test
    void testUpdateExpertSubService() {
        Expert findExpert = expertService.findById(1L);
        SubService subService = subServiceService.findById(1L);
        expertService.updateExpertSubService(subService, findExpert);
        assertThat(expertService.findById(1L).getSubServices().size()).isEqualTo(1);
    }

    @Test
    void testReceivedNewCommentForExpert() {
        Expert findExpert = expertService.findById(1L);
        Comment comment = commentService.findById(1L);
        expertService.receivedNewComment(comment, findExpert);
        assertThat(expertService.findById(1L).getComments().size()).isEqualTo(1);
    }

    @Test
    void testSelectAllAvailableExpert() {
        List<Expert> expertList = expertService.selectAll();
        assertThat(expertList).isNotNull();
        assertThat(expertList.size()).isEqualTo(1);
    }

    @Test
    void login() {
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
        assertThat(expertList.size()).isEqualTo(1);
    }

    @Test
    void testSubmitAnOffer() {
    }

    @Test
    void testGetPersonalPhoto() throws IOException {
        byte[] personalPhoto = expertService.getImage(1L);
        FileOutputStream image=new FileOutputStream("C:\\Users\\paage\\OneDrive\\Documents\\reihaneh\\2222.jpgg");
        image.write(personalPhoto);
    }
}