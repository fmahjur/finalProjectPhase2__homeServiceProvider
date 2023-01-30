package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService extends MainService<Order> {
    void receivedNewOffer(Offer offer, Order order);
    List<Order> selectAllCustomersOrders(Customer customer);

    Optional<Order> getOrderDetail(String orderNumber);
}
