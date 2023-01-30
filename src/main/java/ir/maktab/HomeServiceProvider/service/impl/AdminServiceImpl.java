package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.service.AdminService;
import ir.maktab.HomeServiceProvider.validation.ExpertValidator;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final BaseServiceServiceImpl baseServiceService;
    private final SubServiceServiceImpl subServiceService;
    private final ExpertServiceImpl expertService;
    private final CustomerServiceImpl customerService;

    public AdminServiceImpl(BaseServiceServiceImpl baseServiceService, SubServiceServiceImpl subServiceService, ExpertServiceImpl expertService, CustomerServiceImpl customerService) {
        this.baseServiceService = baseServiceService;
        this.subServiceService = subServiceService;
        this.expertService = expertService;
        this.customerService = customerService;
    }

    @Override
    public BaseService addNewService(BaseService baseService) {
        return baseServiceService.add(baseService);
    }

    @Override
    public SubService addNewSubService(SubService subService) {
        return subServiceService.add(subService);
    }

    @Override
    public void confirmExpert(Expert expert) {
        expert.setExpertStatus(ExpertStatus.ACCEPTED);
        expertService.update(expert);
    }

    @Override
    public void allocationServiceToExpert(Expert expert, SubService subService) {
        //ExpertValidator.isExpertConfirmed(expert.getUsername());

        expert.setSubServices(subService);
        expertService.updateExpertSubService(expert);
    }

    @Override
    public void removeExpertFromService(Expert expert, SubService subService) {
        ExpertValidator.hasASubService(expert, subService);
        expert.getSubServices().remove(subService);
        expertService.update(expert);
    }

    @Override
    public void showAllService() {
        baseServiceService.selectAll();
    }

    @Override
    public void showSubServices(BaseService service) {
        subServiceService.getSubServicesByService(service);
    }

    @Override
    public void showAllCustomer() {
        customerService.selectAll();
    }

    @Override
    public void showAllExpert() {
        expertService.selectAll();
    }

    @Override
    public void showAllNewExpert() {
        expertService.selectExpertByExpertStatus(ExpertStatus.NEW);
    }

    @Override
    public void showAllConfirmedExpert() {
        expertService.selectExpertByExpertStatus(ExpertStatus.ACCEPTED);
    }
}
