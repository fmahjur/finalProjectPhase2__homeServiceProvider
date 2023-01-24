package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.Order;

import java.util.Optional;

public interface OrderService extends MainService<Order> {
    public Optional<Order> getOrderDetail(String orderNumber);
}
