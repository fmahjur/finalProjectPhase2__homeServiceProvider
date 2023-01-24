package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Override
    public void addNewService(BaseService service) {

    }

    @Override
    public void addNewSubService(SubService subService) {

    }

    @Override
    public void confirmExpert(Expert expert) {

    }

    @Override
    public void allocationServiceToExpert(Expert expert, SubService subService) {

    }

    @Override
    public void removeExpertFromService(Expert expert, SubService subService) {

    }

    @Override
    public void showAllService() {

    }

    @Override
    public void showSubServices(BaseService service) {

    }

    @Override
    public void showAllCustomer() {

    }

    @Override
    public void showAllExpert() {

    }

    @Override
    public void showAllNewExpert() {

    }

    @Override
    public void showAllConfirmedExpert() {

    }
}
