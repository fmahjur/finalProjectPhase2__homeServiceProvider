package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.repository.BaseServiceRepository;
import ir.maktab.HomeServiceProvider.exception.NotFoundException;
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
    public BaseService add(BaseService baseService) {
        BaseServiceValidator.isExistService(baseService.getName());
        return baseServiceRepository.save(baseService);
    }

    @Override
    public void remove(BaseService baseService) {
        baseService.setDeleted(true);
        baseServiceRepository.save(baseService);
    }

    @Override
    public BaseService update(BaseService baseService) {
        return baseServiceRepository.save(baseService);
    }

    @Override
    public BaseService findById(Long id) {
        return baseServiceRepository.findById(id).orElseThrow(()->new NotFoundException("not found"));
    }

    @Override
    public List<BaseService> selectAll() {
        return baseServiceRepository.findAll();
    }
    @Override
    public List<BaseService> selectAllAvailableService(){
        return baseServiceRepository.findAllByDeletedIs(false);
    }
}
