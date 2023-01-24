package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.exception.ValidationException;
import ir.maktab.HomeServiceProvider.model.dao.ServicesRepository;

public class ServiceValidatior {
    static ServicesRepository servicesRepository = ServicesRepository.getInstance();

    public static void isExistService(String serviceName) throws ValidationException {
        if (servicesRepository.isExist(serviceName))
            throw new ValidationException("this Service is already Exist!");
    }

    public static void isNotExistService(String service) throws ValidationException {
        if (!servicesRepository.isExist(service))
            throw new ValidationException("this Service is not Exist!");
    }
}
