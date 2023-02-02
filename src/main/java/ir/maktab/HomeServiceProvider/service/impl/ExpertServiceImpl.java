package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Comment;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Orders;
import ir.maktab.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.HomeServiceProvider.exception.ResourceNotFoundException;
import ir.maktab.HomeServiceProvider.service.ExpertService;
import ir.maktab.HomeServiceProvider.validation.EmailValidator;
import ir.maktab.HomeServiceProvider.validation.PasswordValidator;
import ir.maktab.HomeServiceProvider.validation.PictureValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;
    private final OrderServiceImpl orderService;
    private final CommentServiceImp commentService;

    @Override
    public Expert add(Expert expert) {
        EmailValidator.isValid(expert.getEmailAddress());
        //ExpertValidator.isExistExpert(expert.getEmailAddress());
        Optional<Expert> savedExpert = expertRepository.findByEmailAddress(expert.getEmailAddress());
        if (savedExpert.isPresent()) {
            throw new ResourceNotFoundException("Employee already exist with given email:" + expert.getEmailAddress());
        }
        PasswordValidator.isValid(expert.getPassword());
        PictureValidator.isValidImageSize(expert.getPersonalPhoto());
        return expertRepository.save(expert);
    }

    @Override
    public void remove(Expert expert) {
        expert.setDeleted(true);
        expertRepository.save(expert);
    }

    @Override
    public Expert update(Expert expert) {
        return expertRepository.save(expert);
    }

    @Override
    public void updateExpertSubService(Expert expert) {
        expertRepository.updateExpertSubService(expert.getSubServices(), expert.getUsername());
    }

    public void receivedNewComment(Comment comment, Expert expert) {
        comment.setExpert(expert);
        expert.getComments().add(comment);
        expert.setRate();
        commentService.add(comment);
        expertRepository.save(expert);
    }

    @Override
    public List<Expert> selectAll() {
        return expertRepository.findAll();
    }

    @Override
    public List<Expert> selectAllAvailableService() {
        return expertRepository.findAllByDeletedIs(false);
    }


    @Override
    public void login(Expert expert) {
        Optional<Expert> expertByUsername = expertRepository.findByUsername(expert.getUsername());
        if (expertByUsername.isPresent())
            if (!Objects.equals(expert.getPassword(), expertByUsername.get().getPassword()))
                throw new IncorrectInformationException("Username or Password is Incorrect!");

    }

    @Override
    public Expert changePassword(Expert expert, String newPassword, String confirmNewPassword) {
        PasswordValidator.isValidNewPassword(expert.getPassword(), newPassword, confirmNewPassword);
        PasswordValidator.isValid(newPassword);
        expert.setPassword(newPassword);
        return expertRepository.save(expert);
    }

    @Override
    public List<Expert> selectExpertByExpertStatus(ExpertStatus expertStatus) {
        return expertRepository.findAllByExpertStatus(expertStatus);
    }

    public void submitAnOffer(Offer offer, Orders orders) {
        orderService.receivedNewOffer(offer, orders);
    }
}
