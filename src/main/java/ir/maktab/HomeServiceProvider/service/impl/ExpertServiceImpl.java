package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.HomeServiceProvider.service.ExpertService;
import ir.maktab.HomeServiceProvider.validation.EmailValidator;
import ir.maktab.HomeServiceProvider.validation.ExpertValidator;
import ir.maktab.HomeServiceProvider.validation.PasswordValidator;
import ir.maktab.HomeServiceProvider.validation.PictureValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;

    @Override
    public void add(Expert expert) {
        EmailValidator.isValid(expert.getEmailAddress());
        ExpertValidator.isExistExpert(expert.getEmailAddress());
        PasswordValidator.isValid(expert.getPassword());
        PictureValidator.isValidImageSize(expert.getPersonalPhoto());
        expertRepository.save(expert);
    }

    @Override
    public void delete(Expert expert) {
        expert.setDeleted(true);
        expertRepository.save(expert);
    }

    @Override
    public void update(Expert expert) {
        expertRepository.save(expert);
    }

    @Override
    public List<Expert> selectAll() {
        return expertRepository.findAll();
    }

    @Override
    public void login(Expert expert) {
        Expert expertByUsername = expertRepository.findByUsername(expert.getUsername());
        if (!Objects.equals(expert.getPassword(), expertByUsername.getPassword()))
            throw new IncorrectInformationException("Username or Password is Incorrect!");
    }

    @Override
    public void changePassword(Expert expert, String newPassword, String confirmNewPassword) {
        PasswordValidator.isValidNewPassword(expert.getPassword(), newPassword, confirmNewPassword);
        PasswordValidator.isValid(newPassword);
        expert.setPassword(newPassword);
        expertRepository.save(expert);
    }

    @Override
    public List<Expert> selectExpertByExpertStatus(ExpertStatus expertStatus) {
        return expertRepository.findAllByExpertStatus(expertStatus);
    }
}
