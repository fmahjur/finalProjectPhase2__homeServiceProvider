package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Order;
import ir.maktab.HomeServiceProvider.data.repository.OrderRepository;
import ir.maktab.HomeServiceProvider.service.OrderService;
import ir.maktab.HomeServiceProvider.validation.OfferValidator;
import ir.maktab.HomeServiceProvider.validation.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OfferServiceImpl offerService;
    @Override
    public Order add(Order order) {
        OrderValidator.isValidOrderStartDate(order.getWorkStartDate());
        OrderValidator.isValidCustomerProposedPrice(order);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        order.setDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    public void receivedNewOffer(Offer offer, Order order){
        OrderValidator.checkOrderStatus(order);
        OfferValidator.isValidExpertProposedPrice(offer);
        OfferValidator.hasDurationOfWork(offer);
        offer.setOrder(order);
        order.getOffers().add(offer);
        order.setOrderStatus(OrderStatus.WAITING_FOR_CHOSE_EXPERT);
        offerService.add(offer);
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
    public Optional<Order> getOrderDetail(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public void showAllOfferForOrder(Order order){
        offerService.selectAllByOrder(order);
    }
}
