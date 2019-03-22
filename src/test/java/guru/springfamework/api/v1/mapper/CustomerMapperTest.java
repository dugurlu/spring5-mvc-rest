package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {


    public static final String FIRST_NAME = "Joe";
    public static final String LAST_NAME = "Buck";
    public static final Long ID = 1L;
    public static final String URL = "/customers/1";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {
        // given
        Customer customer = new Customer();
        customer.setFirstname(FIRST_NAME);
        customer.setLastname(LAST_NAME);
        customer.setId(ID);

        // when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);

        // then
        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRST_NAME, customerDTO.getFirstname());
        assertEquals(LAST_NAME, customerDTO.getLastname());

    }
}
