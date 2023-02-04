package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.data.repository.ExpertRepository;
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
    @Autowired
    private ExpertRepository expertRepository0;

    @PostConstruct
    private void initStaticExpertRepository() {
        expertRepository = expertRepository0;
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
        expertRepository.findById(expert.getId());
        //throw new ValidationException("this Expert does not have this subService!");
    }
}
