package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.HomeServiceProvider.exception.ValidationException;

public class OfferValidator {
    public static void isValidExpertProposedPrice(Offer offer) throws ValidationException {
        if (!(offer.getOfferPrice() >= offer.getOrders().getSubService().getBasePrice()))
            throw new ValidationException("The proposed price cannot be less than the base price!");
    }

    public static void hasDurationOfWork(Offer offer){
        if (offer.getDurationOfWork() == 0) {
            throw new IncorrectInformationException("Your offer must include the duration of the work");
        }
    }
}
