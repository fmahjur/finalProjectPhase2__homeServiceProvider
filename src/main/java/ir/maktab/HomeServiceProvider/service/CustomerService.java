package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Order;

public interface CustomerService extends MainService<Customer> {
    void login(Customer customer);

    void changePassword(Customer customer, String newPassword, String confirmNewPassword);

    void addNewOrder(Order order);

    void deleteOrder(Order order);

    void editOrder(Order order);

    void showAllCustomerOrders(Customer customer);

    void showOrderDetails(String orderNumber);

    void showAllAvailableService();

    void showSubServices(BaseService service);
}
