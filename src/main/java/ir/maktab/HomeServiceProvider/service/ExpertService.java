package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.*;

import java.util.List;

public interface ExpertService extends MainService<Expert> {
    void updateExpertSubService(SubService subService, Expert expert);

    void receivedNewComment(Comment comment, Expert expert);

    void login(Expert expert);

    Expert changePassword(Expert expert, String newPassword, String confirmNewPassword);

    List<Expert> selectExpertByExpertStatus(ExpertStatus expertStatus);

    void submitAnOffer(Offer offer, Orders orders);

    public byte[] getImage(Long id);
}
