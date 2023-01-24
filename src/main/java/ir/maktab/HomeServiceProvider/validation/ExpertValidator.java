package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import ir.maktab.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpertValidator {
    private static ExpertRepository expertRepository;

    public static void isExistExpert(String email) throws ValidationException {
        if (expertRepository.isExist(email))
            throw new ValidationException("this Email is already Exist!");
    }

    public static void isExpertConfirmed(String username) throws ValidationException {
        if (expertRepository.isExist(username))
            throw new ValidationException("this Expert is not confirmed!");
    }

    public static void hasASubService(Expert expert, SubService subService){
        if (!expert.getSubServices().contains(subService.getName()))
            throw new ValidationException("this Expert does not have this subService!");
    }
}
