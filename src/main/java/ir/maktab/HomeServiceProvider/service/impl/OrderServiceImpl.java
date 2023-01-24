package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Order;
import ir.maktab.HomeServiceProvider.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Override
    public void add(Order order) {

    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public List<Order> selectAll() {
        return null;
    }

    @Override
    public Optional<Order> getOrderDetail(String orderNumber) {
        return Optional.empty();
    }
}
