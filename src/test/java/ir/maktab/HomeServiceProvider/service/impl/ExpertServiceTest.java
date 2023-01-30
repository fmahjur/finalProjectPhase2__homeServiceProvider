package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Credit;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.HomeServiceProvider.exception.ResourceNotFoundException;
import ir.maktab.HomeServiceProvider.validation.PictureValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExpertServiceTest {
    @Mock
    private ExpertRepository expertRepository;

    @InjectMocks
    private ExpertServiceImpl expertService;

    private Expert expert;

    @BeforeEach
    void setUp() {
        expert = new Expert(
                "reihan",
                "mahjour",
                "reihaneh763@gmail.com",
                "Rm@123456",
                new Credit(1000L),
                getImage());
    }

    @Test
    void givenExpertObjectWhenSaveExpertThenReturnExpertObject() {
        // given
        given(expertRepository.findByEmailAddress(expert.getEmailAddress()))
                .willReturn(Optional.empty());

        given(expertRepository.save(expert)).willReturn(expert);

        // when
        Expert savedExpert = expertService.add(expert);

        // then
        assertThat(savedExpert).isNotNull();
    }

    @Test
    public void givenExistingEmailWhenSaveExpertThenThrowsException() {
        // given
        given(expertRepository.findByEmailAddress(expert.getEmailAddress()))
                .willReturn(Optional.of(expert));

        // when
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            expertService.add(expert);
        });

        // then
        verify(expertRepository, never()).save(any(Expert.class));
    }

    @Test
    void delete() {
    }

    @Test
    public void givenExpertObjectWhenUpdateExpertThenReturnUpdatedExpert(){
        // given
        given(expertRepository.save(expert)).willReturn(expert);
        expert.setEmailAddress("reza@gmail.com");
        expert.setFirstname("reza");

        // when
        Expert updatedExpert = expertService.update(expert);

        // then
        assertThat(updatedExpert.getEmailAddress()).isEqualTo("reza@gmail.com");
        assertThat(updatedExpert.getFirstname()).isEqualTo("reza");
    }

    @Test
    public void givenExpertsListWhenGetAllExpertsThenReturnExpertsList() {
        // given
        Expert expert1 = new Expert(
                "ali",
                "mahjur",
                "mahjur@gmail.com",
                "Am@123456",
                new Credit(1500L),
                getImage());

        given(expertRepository.findAll()).willReturn(List.of(expert, expert1));

        // when
        List<Expert> expertList = expertService.selectAll();

        // then
        assertThat(expertList).isNotNull();
        assertThat(expertList.size()).isEqualTo(2);
    }

    @Test
    public void givenEmptyExpertsListWhenGetAllExpertsThenReturnEmptyExpertsList(){
        // given
        Expert expert1 = new Expert(
                "ali",
                "mahjur",
                "mahjur@gmail.com",
                "Am@123456",
                new Credit(1500L),
                getImage());

        given(expertRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<Expert> expertList = expertService.selectAll();

        // then
        assertThat(expertList).isEmpty();
        assertThat(expertList.size()).isEqualTo(0);
    }

    @Test
    void login() {
    }

    @Test
    void givenExpertObjectWhenChangePasswordThenReturnUpdatedExpert() {
        // given
        given(expertRepository.save(expert)).willReturn(expert);
        String newPassword = "AAaa!1234";
        String confirmNewPassword = "AAaa!1234";

        // when
        Expert updatedExpert = expertService.changePassword(expert, newPassword, confirmNewPassword);

        // then
        assertThat(updatedExpert.getPassword()).isEqualTo("AAaa!1234");
    }

    @Test
    void givenExpertsListWhenSelectedAllExpertsByExpertStatusThenReturnExpertsList() {
        // given
        Expert expert1 = new Expert(
                "ali",
                "mahjur",
                "mahjur@gmail.com",
                "Am@123456",
                new Credit(1500L),
                getImage());

        given(expertRepository.findAllByExpertStatus(ExpertStatus.NEW)).willReturn(List.of(expert, expert1));

        // when
        List<Expert> expertList = expertService.selectExpertByExpertStatus(ExpertStatus.NEW);

        // then
        assertThat(expertList).isNotNull();
        assertThat(expertList.size()).isEqualTo(2);
    }

    private byte[] getImage() {
        String imageFilePath = "C:\\Users\\reiha\\Desktop\\1111.jpg";
        PictureValidator.isValidImageFile(imageFilePath);
        File file = new File(imageFilePath);
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bFile;
    }
}