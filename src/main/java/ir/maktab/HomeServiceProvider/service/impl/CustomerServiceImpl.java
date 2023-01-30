package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.enums.OfferStatus;
import ir.maktab.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Order;
import ir.maktab.HomeServiceProvider.data.repository.CustomerRepository;
import ir.maktab.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.HomeServiceProvider.service.CustomerService;
import ir.maktab.HomeServiceProvider.validation.CustomerValidator;
import ir.maktab.HomeServiceProvider.validation.EmailValidator;
import ir.maktab.HomeServiceProvider.validation.OrderValidator;
import ir.maktab.HomeServiceProvider.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderServiceImpl orderService;
    private final OfferServiceImpl offerService;
    private final BaseServiceServiceImpl baseServiceService;
    private final SubServiceServiceImpl subServiceService;

    @Override
    public Customer add(Customer customer) {
        EmailValidator.isValid(customer.getEmailAddress());
        CustomerValidator.isExistCustomer(customer.getEmailAddress());
        PasswordValidator.isValid(customer.getPassword());
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customer.setDeleted(true);
        customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> selectAll() {
        return customerRepository.findAll();
    }

    @Override
    public void login(Customer customer) {
        Customer customerByUsername = customerRepository.findByUsername(customer.getUsername()).orElseThrow(
                () -> new NotFoundException("Not Found your username!")
        );
        if (!Objects.equals(customer.getPassword(), customerByUsername.getPassword()))
            throw new IncorrectInformationException("Username or Password is Incorrect!");
    }

    @Override
    public void changePassword(Customer customer, String newPassword, String confirmNewPassword) {
        PasswordValidator.isValidNewPassword(customer.getPassword(), newPassword, confirmNewPassword);
        PasswordValidator.isValid(newPassword);
        customer.setPassword(newPassword);
        customerRepository.save(customer);
    }

    @Override
    public void addNewOrder(Order order) {
        orderService.add(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderService.delete(order);
    }

    @Override
    public void editOrder(Order order) {
        orderService.update(order);
    }

    @Override
    public void choseAnExpertForOrder(Order order, Offer offer) {
        for (Offer offer1 : order.getOffers()) {
            if (Objects.equals(offer1, offer)) {
                offer1.setOfferStatus(OfferStatus.ACCEPTED);
                order.setWorkStartDate(offer1.getProposedStartDate());
            }
            else
                offer1.setOfferStatus(OfferStatus.REJECTED);
            offerService.update(offer1);
        }
        order.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_TO_COME);
        orderService.update(order);
    }
    @Override
    public void changeOrderStatusToStarted(Order order){
        OrderValidator.isValidOrderStartDate(order.getWorkStartDate());
        order.setOrderStatus(OrderStatus.STARTED);
        orderService.update(order);
    }
    @Override
    public void changeOrderStatusToDone(Order order){
        order.setOrderStatus(OrderStatus.DONE);
        orderService.update(order);
    }

    @Override
    public void showAllCustomerOrders(Customer customer) {
        orderService.selectAllCustomersOrders(customer);
    }

    @Override
    public void showOrderDetails(String orderNumber) {
        orderService.getOrderDetail(orderNumber);
    }

    @Override
    public void showAllAvailableService() {
        baseServiceService.selectAll();
    }

    @Override
    public void showSubServices(BaseService service) {
        subServiceService.getSubServicesByService(service);
    }
}
