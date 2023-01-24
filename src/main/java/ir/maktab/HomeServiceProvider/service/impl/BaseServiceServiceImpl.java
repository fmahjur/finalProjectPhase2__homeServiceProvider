package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.service.BaseServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseServiceServiceImpl implements BaseServiceService {
    @Override
    public void add(BaseService baseService) {

    }

    @Override
    public void delete(BaseService baseService) {

    }

    @Override
    public void update(BaseService baseService) {

    }

    @Override
    public List<BaseService> selectAll() {
        return null;
    }
}
