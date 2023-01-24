package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Expert;

import java.util.List;

public interface ExpertService extends MainService<Expert> {
    void login(Expert expert);

    void changePassword(Expert expert, String newPassword);

    List<Expert> selectExpertByExpertStatus(ExpertStatus expertStatus);
}
