package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.data.repository.CustomerRepository;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidator {
    private static CustomerRepository customerRepository;

    public static void isExistCustomer(String email) throws ValidationException {
        if (customerRepository.isExist(email))
            throw new ValidationException("this Email is already Exist!");
    }
}
