package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.data.repository.SubServiceRepository;
import ir.maktab.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.HomeServiceProvider.service.SubServiceService;
import ir.maktab.HomeServiceProvider.validation.BaseServiceValidator;
import ir.maktab.HomeServiceProvider.validation.SubServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;

    @Override
    public SubService add(SubService subService) {
        BaseServiceValidator.isNotExistService(subService.getBaseService().getName());
        SubServiceValidator.isExistSubService(subService.getName());
        return subServiceRepository.save(subService);
    }

    @Override
    public void remove(SubService subService) {
        subService.setDeleted(true);
        subServiceRepository.save(subService);
    }

    @Override
    public SubService update(SubService subService) {
        return subServiceRepository.save(subService);
    }

    @Override
    public SubService findById(Long id) {
        return subServiceRepository.findById(id).orElseThrow(()->new NotFoundException("not found"));
    }

    @Override
    public List<SubService> selectAll() {
        return subServiceRepository.findAll();
    }

    @Override
    public List<SubService> selectAllAvailableService() {
        return subServiceRepository.findAllByDeletedIs(false);
    }

    @Override
    public List<SubService> getSubServicesByService(BaseService service) {
        return subServiceRepository.findAllByBaseService(service);
    }
}
