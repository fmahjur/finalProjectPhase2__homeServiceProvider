package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Orders;
import ir.maktab.HomeServiceProvider.data.repository.OrderRepository;
import ir.maktab.HomeServiceProvider.exception.NotFoundException;
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
    public Orders add(Orders orders) {
        OrderValidator.isValidOrderStartDate(orders.getWorkStartDate());
        OrderValidator.isValidCustomerProposedPrice(orders);
        return orderRepository.save(orders);
    }

    @Override
    public void remove(Orders orders) {
        orders.setDeleted(true);
        orderRepository.save(orders);
    }

    @Override
    public Orders update(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public Orders findById(Long id) {
        return orderRepository.findById(id).orElseThrow(()->new NotFoundException("not found"));
    }

    @Override
    public void receivedNewOffer(Offer offer, Orders orders) {
        OrderValidator.checkOrderStatus(orders);
        OfferValidator.isValidExpertProposedPrice(offer);
        OfferValidator.hasDurationOfWork(offer);
        offer.setOrders(orders);
        orders.getOffers().add(offer);
        orders.setOrderStatus(OrderStatus.WAITING_FOR_CHOSE_EXPERT);
        offerService.add(offer);
        orderRepository.save(orders);
    }

    @Override
    public List<Orders> selectAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> selectAllAvailableService() {
        return orderRepository.findAllByDeletedIs(false);
    }

    @Override
    public List<Orders> selectAllCustomersOrders(Customer customer) {
        return orderRepository.findAllByCustomer(customer);
    }

    @Override
    public Optional<Orders> getOrderDetail(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }
    @Override
    public void showAllOfferForOrder(Orders orders) {
        offerService.selectAllByOrder(orders);
    }
}
