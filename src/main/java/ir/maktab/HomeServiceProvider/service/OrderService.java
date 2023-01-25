package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService extends MainService<Order> {
    List<Order> selectAllCustomersOrders(Customer customer);

    Order getOrderDetail(String orderNumber);
}
