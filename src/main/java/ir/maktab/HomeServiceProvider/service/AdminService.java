package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;

public interface AdminService {
    BaseService addNewService(BaseService service);

    SubService addNewSubService(SubService subService);

    void confirmExpert(Expert expert);

    void allocationServiceToExpert(Expert expert, SubService subService);

    void removeExpertFromService(Expert expert, SubService subService);

    void showAllService();

    void showSubServices(BaseService service);

    void showAllCustomer();

    void showAllExpert();

    void showAllNewExpert();

    void showAllConfirmedExpert();
}
