package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Order;

import java.util.List;

public interface ExpertService extends MainService<Expert> {
    void login(Expert expert);

    Expert changePassword(Expert expert, String newPassword, String confirmNewPassword);

    List<Expert> selectExpertByExpertStatus(ExpertStatus expertStatus);

    void submitAnOffer(Offer offer, Order order);
}
