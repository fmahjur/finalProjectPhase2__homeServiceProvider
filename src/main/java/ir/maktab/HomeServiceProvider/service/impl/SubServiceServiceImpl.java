package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.data.repository.SubServiceRepository;
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
    public void add(SubService subService) {
        BaseServiceValidator.isNotExistService(subService.getBaseService().getName());
        SubServiceValidator.isExistSubService(subService.getName());
        subServiceRepository.save(subService);
    }

    @Override
    public void delete(SubService subService) {
        subService.setDeleted(true);
        subServiceRepository.save(subService);
    }

    @Override
    public void update(SubService subService) {
        subServiceRepository.save(subService);
    }

    @Override
    public List<SubService> selectAll() {
        return subServiceRepository.findAll();
    }

    @Override
    public List<SubService> getSubServicesByService(BaseService service) {
        return subServiceRepository.findAllByBaseService(service);
    }
}
