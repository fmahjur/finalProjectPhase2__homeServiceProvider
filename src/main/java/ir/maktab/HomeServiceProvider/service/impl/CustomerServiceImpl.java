package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Order;
import ir.maktab.HomeServiceProvider.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void login(Customer customer) {

    }

    @Override
    public void changePassword(Customer customer, String newPassword) {

    }

    @Override
    public void addNewOrder(Order order) {

    }

    @Override
    public void deleteOrder(Order order) {

    }

    @Override
    public void editOrder(Order order) {

    }

    @Override
    public void showAllOrders() {

    }

    @Override
    public void showOrderDetails(String orderNumber) {

    }

    @Override
    public void showAllAvailableService() {

    }

    @Override
    public void showSubServices(BaseService service) {

    }

    @Override
    public void add(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public List<Customer> selectAll() {
        return null;
    }
}
