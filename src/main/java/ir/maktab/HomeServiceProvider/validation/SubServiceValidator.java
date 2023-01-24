package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.data.repository.SubServiceRepository;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubServiceValidator {
    private static SubServiceRepository subServiceRepository;

    public static void isExistSubService(String subServiceName) throws ValidationException {
        if (subServiceRepository.findByName(subServiceName) != null)
            throw new ValidationException("this SubService is already Exist!");
    }
}
