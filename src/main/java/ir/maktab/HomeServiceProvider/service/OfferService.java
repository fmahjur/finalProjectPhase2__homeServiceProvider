package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Order;

import java.util.List;

public interface OfferService extends MainService<Offer> {
    List<Offer> selectAllByOrder(Order order);
    List<Offer> selectAllByExpert(Expert expert);
}
