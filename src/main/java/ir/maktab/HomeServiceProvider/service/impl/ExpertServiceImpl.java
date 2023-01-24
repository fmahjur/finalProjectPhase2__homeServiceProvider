package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {
    @Override
    public void login(Expert expert) {

    }

    @Override
    public void changePassword(Expert expert, String newPassword) {

    }

    @Override
    public List<Expert> selectExpertByExpertStatus(ExpertStatus expertStatus) {
        return null;
    }

    @Override
    public void add(Expert expert) {

    }

    @Override
    public void delete(Expert expert) {

    }

    @Override
    public void update(Expert expert) {

    }

    @Override
    public List<Expert> selectAll() {
        return null;
    }
}
