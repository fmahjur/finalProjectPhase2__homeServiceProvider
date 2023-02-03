package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService extends MainService<Orders> {
    void receivedNewOffer(Offer offer, Orders orders);

    List<Orders> selectAllCustomersOrders(Customer customer);

    Optional<Orders> getOrderDetail(String orderNumber);

    void showAllOfferForOrder(Orders orders);
}
