package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.Credit;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveMethod() throws InterruptedException {
        // given
        Customer customer = getCustomer();

        // when
        Customer saveCustomer = customerRepository.save(customer);

        // then
        assertThat(saveCustomer.getFirstname()).isEqualTo(customer.getFirstname());
        assertThat(saveCustomer.getLastname()).isEqualTo(customer.getLastname());
        assertThat(saveCustomer.getEmailAddress()).isEqualTo(customer.getEmailAddress());
        assertThat(saveCustomer.getPassword()).isEqualTo(customer.getPassword());
        assertThat(saveCustomer.getCredit().getBalance()).isEqualTo(customer.getCredit().getBalance());
    }

    @Test
    void testUpdateMethod() throws InterruptedException {
        // given
        Customer customer = getCustomer2();
        Customer saveCustomer = customerRepository.save(customer);

        // when
        saveCustomer.setFirstname("reihaneh");
        saveCustomer.setCredit(new Credit(2000L));
        Customer updateCustomer = customerRepository.save(saveCustomer);

        // then
        assertThat(updateCustomer.getFirstname()).isEqualTo("reihaneh");
        assertThat(updateCustomer.getCredit().getBalance()).isEqualTo(2000L);
    }

    @Test
    void testFindByIdMethod(){
        // given
        Customer customer = getCustomer1();
        customerRepository.save(customer);

        // when
        Customer findCustomer = customerRepository.findById(customer.getId()).orElse(null);

        // then
        assertNotNull(findCustomer);

    }

    @Test
    void testFindAllMethod(){
        // then
        List<Customer> customerList = customerRepository.findAll();

        // when
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(3);
    }

    @Test
    void findByUsername() {
        // when
        Customer findCustomer = customerRepository.findByUsername("f.mahjur@gmail.com").orElse(null);

        // then
        assertNotNull(findCustomer);
    }

    @Test
    void findByEmailAddress() {
        // when
        Customer findCustomer = customerRepository.findByEmailAddress("mahjur@gmail.com").orElse(null);

        // then
        assertNotNull(findCustomer);
    }

    protected Customer getCustomer(){
        return new Customer("fatemeh", "mahjour", "f.mahjur@gmail.com", "Fm@38936", new Credit(1500L));
    }
    protected Customer getCustomer1(){
        return new Customer("ali", "mahjour", "mahjur@gmail.com", "Alim@123456", new Credit(2500L));
    }
    protected Customer getCustomer2(){
        return new Customer("narges", "jamshidi", "nargess@gmail.com", "Nj!123456", new Credit(1500L));
    }
}