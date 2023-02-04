package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.Utils.DateUtil;
import ir.maktab.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.HomeServiceProvider.data.model.Orders;
import ir.maktab.HomeServiceProvider.exception.ValidationException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class OrderValidator {
    public static void isValidOrderStartDate(Date workStartDate) throws ValidationException {
        if (!workStartDate.before(DateUtil.asDate(LocalDate.now())))
            throw new ValidationException("work can not start before now!");
    }

    public static void isValidCustomerProposedPrice(Orders orders) throws ValidationException {
        if (!(orders.getCustomerProposedPrice() >= orders.getSubService().getBasePrice()))
            throw new ValidationException("The proposed price cannot be less than the base price!");
    }

    public static void checkOrderStatus(Orders orders){
        if (!(orders.getOrderStatus().equals(OrderStatus.WAITING_FOR_EXPERTS_OFFER) ||
            orders.getOrderStatus().equals(OrderStatus.WAITING_FOR_CHOSE_EXPERT)))
            throw new ValidationException("This order accepted by an other expert!");
    }
}
