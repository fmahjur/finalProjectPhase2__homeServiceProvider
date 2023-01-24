package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.SubService;

import java.util.List;

public interface SubServiceService extends MainService<SubService> {
    public List<SubService> getSubServicesByService(BaseService service);
}
