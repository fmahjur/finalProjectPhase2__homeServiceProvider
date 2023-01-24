package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {
    @Override
    public void add(SubService subService) {

    }

    @Override
    public void delete(SubService subService) {

    }

    @Override
    public void update(SubService subService) {

    }

    @Override
    public List<SubService> selectAll() {
        return null;
    }

    @Override
    public List<SubService> getSubServicesByService(BaseService service) {
        return null;
    }
}
