package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.repository.BaseServiceRepository;
import ir.maktab.HomeServiceProvider.service.BaseServiceService;
import ir.maktab.HomeServiceProvider.validation.BaseServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseServiceServiceImpl implements BaseServiceService {
    private final BaseServiceRepository baseServiceRepository;

    @Override
    public void add(BaseService baseService) {
        BaseServiceValidator.isExistService(baseService.getName());
        baseServiceRepository.save(baseService);
    }

    @Override
    public void delete(BaseService baseService) {
        baseService.setDeleted(true);
        update(baseService);
    }

    @Override
    public void update(BaseService baseService) {
        BaseService existingBaseService = baseServiceRepository.findById(baseService.getId()).orElse(null);
        existingBaseService.setName(baseService.getName());
        existingBaseService.setDeleted(baseService.isDeleted());
        existingBaseService.setSubServiceList(baseService.getSubServiceList());
        baseServiceRepository.save(existingBaseService);
    }

    @Override
    public List<BaseService> selectAll() {
        return baseServiceRepository.findAll();
    }
}
