package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.data.model.Order;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    public static void isValidOrderStartDate(Date workStartDate) throws ValidationException {
        if (!workStartDate.before(Date.from(Instant.from(LocalDate.now()))))
            throw new ValidationException("work can not start before now!");
    }

    public static void isValidCustomerProposedPrice(Order order) throws ValidationException {
        if (!(order.getCustomerProposedPrice() >= order.getSubService().getBasePrice()))
            throw new ValidationException("work can not start before now!");
    }
}
