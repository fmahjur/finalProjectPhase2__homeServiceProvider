package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Order;
import ir.maktab.HomeServiceProvider.data.repository.OrderRepository;
import ir.maktab.HomeServiceProvider.service.OrderService;
import ir.maktab.HomeServiceProvider.validation.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public void add(Order order) {
        OrderValidator.isValidOrderStartDate(order.getWorkStartDate());
        OrderValidator.isValidCustomerProposedPrice(order);
        orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        order.setDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> selectAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> selectAllCustomersOrders(Customer customer) {
        return orderRepository.findAllByCustomer(customer);
    }

    @Override
    public Order getOrderDetail(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }
}
