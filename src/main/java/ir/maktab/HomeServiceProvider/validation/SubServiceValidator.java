package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.exception.ValidationException;
import ir.maktab.HomeServiceProvider.model.dao.SubServiceRepository;

public class SubServiceValidator {
    static SubServiceRepository subServiceRepository = SubServiceRepository.getInstance();

    public static void isExistSubService(String service) throws ValidationException {
        if (subServiceRepository.isExist(service))
            throw new ValidationException("this SubService is already Exist!");
    }
}
