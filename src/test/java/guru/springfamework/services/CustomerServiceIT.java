package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class CustomerServiceIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() {
        log.debug("Loading Data");
        log.debug("Customers: " + customerRepository.findAll().size());

        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run(null);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerFirstname () {
        String newName = "NewName";
        Long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(newName);

        customerService.patchCustomer(id, customerDTO);

        Customer patchedCustomer = customerRepository.getOne(id);

        assertNotNull(patchedCustomer);
        assertEquals(newName, patchedCustomer.getFirstname());
        assertThat(originalFirstname, not(equalTo(patchedCustomer.getFirstname())));
        assertThat(originalLastname, equalTo(patchedCustomer.getLastname()));
    }

    @Test
    public void patchCustomerLastname () {
        String newName = "NewName";
        Long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(newName);

        customerService.patchCustomer(id, customerDTO);

        Customer patchedCustomer = customerRepository.getOne(id);

        assertNotNull(patchedCustomer);
        assertEquals(newName, patchedCustomer.getLastname());
        assertThat(originalLastname, not(equalTo(patchedCustomer.getLastname())));
        assertThat(originalFirstname, equalTo(patchedCustomer.getFirstname()));

    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();

        return customers.get(0).getId();
    }
}
