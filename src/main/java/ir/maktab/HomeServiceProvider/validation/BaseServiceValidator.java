package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.data.repository.BaseServiceRepository;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseServiceValidator {
    private static BaseServiceRepository baseServiceRepository;

    public static void isExistService(String serviceName) throws ValidationException {
        if (baseServiceRepository.findByName(serviceName) != null)
            throw new ValidationException("this Service is already Exist!");
    }

    public static void isNotExistService(String serviceName) throws ValidationException {
        if (baseServiceRepository.findByName(serviceName) == null)
            throw new ValidationException("this Service is not Exist!");
    }
}
