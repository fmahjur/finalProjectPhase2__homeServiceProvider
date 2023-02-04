package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.HomeServiceProvider.data.repository.SubServiceRepository;
import ir.maktab.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExpertValidator {
    private static ExpertRepository expertRepository;
    private static SubServiceRepository subServiceRepository;
    @Autowired
    private ExpertRepository expertRepository0;
    @Autowired
    private SubServiceRepository subServiceRepository0;

    @PostConstruct
    private void initStaticRepository() {
        expertRepository = expertRepository0;
        subServiceRepository = subServiceRepository0;
    }

    public static void isExistExpert(String email) throws ValidationException {
        if (expertRepository.existsByEmailAddress(email))
            throw new ValidationException("this Email is already Exist!");
    }

    public static void isExpertConfirmed(Expert expert) throws ValidationException {
        Optional<Expert> optionalExpert = expertRepository.findById(expert.getId());
        if (optionalExpert.get().getExpertStatus().equals(String.valueOf(ExpertStatus.ACCEPTED)))
            throw new ValidationException("this Expert is not confirmed!");
    }

    public static void hasASubService(Expert expert, SubService subService) {
        Expert existExpert = expertRepository.findById(expert.getId()).orElseThrow(
                ()->new NotFoundException("not found this expert!")
        );

        SubService existSubService = subServiceRepository.findById(subService.getId()).orElseThrow(
                () -> new NotFoundException("not found this subService!")
        );

        if(existExpert.getSubServices().contains(existSubService))
            throw new ValidationException("this Expert does not have this subService!");
        throw new ValidationException("this Expert does not have this subService!");
    }
}
