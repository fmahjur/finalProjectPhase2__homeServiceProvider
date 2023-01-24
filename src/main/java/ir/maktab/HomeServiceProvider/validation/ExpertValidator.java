package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.exception.ValidationException;
import ir.maktab.HomeServiceProvider.model.dao.ExpertRepository;
import ir.maktab.HomeServiceProvider.model.entity.Expert;
import ir.maktab.HomeServiceProvider.model.entity.SubService;

public class ExpertValidator {
    static ExpertRepository expertRepository = ExpertRepository.getInstance();

    public static void isExistExpert(String email) throws ValidationException {
        if (expertRepository.isExist(email))
            throw new ValidationException("this Email is already Exist!");
    }

    public static void isExpertConfirmed(String username) throws ValidationException {
        if (expertRepository.isExist(username))
            throw new ValidationException("this Expert is not confirmed!");
    }

    //Add this Method:
    public static void hasASubService(Expert expert, SubService subService){
        if (!expert.getSubServices().contains(subService.getName()))
            throw new ValidationException("this Expert does not have this subService!");
    }
}
