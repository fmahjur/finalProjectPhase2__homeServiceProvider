package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Orders;

import java.util.List;

public interface OfferService extends MainService<Offer> {
    List<Offer> selectAllByOrder(Orders orders);
    List<Offer> selectAllByExpert(Expert expert);
}
