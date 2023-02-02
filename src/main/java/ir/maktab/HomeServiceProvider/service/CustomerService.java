package ir.maktab.HomeServiceProvider.service;

import ir.maktab.HomeServiceProvider.data.model.*;

public interface CustomerService extends MainService<Customer> {
    void login(Customer customer);

    void changePassword(Customer customer, String newPassword, String confirmNewPassword);

    void addNewOrder(Orders orders);

    void deleteOrder(Orders orders);

    void editOrder(Orders orders);

    void choseAnExpertForOrder(Orders orders, Offer offer);

    void changeOrderStatusToStarted(Orders orders);

    void changeOrderStatusToDone(Orders orders);

    void addNewComment(Comment comment, Expert expert);

    void showAllCustomerOrders(Customer customer);

    void showOrderDetails(String orderNumber);

    void showAllAvailableService();

    void showSubServices(BaseService service);
}
