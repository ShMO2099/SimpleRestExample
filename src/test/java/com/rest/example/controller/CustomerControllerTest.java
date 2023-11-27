package com.rest.example.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.example.model.Customer;
import com.rest.example.service.CustomerService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerControllerTest {

  @InjectMocks private CustomerController customerController;

  @Mock
  private CustomerService customerService;
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
  }

  @Test
  public void testGetAllCustomers() throws Exception {
    Customer customer1 = Customer.builder()
        .id(1)
        .name("cust 1")
        .addressLineOne("1 test street")
        .town("1 test town")
        .county("1 test county")
        .country("1 test country")
        .postcode("1 test postcode")
        .build();
    Customer customer2 = Customer.builder()
        .id(2)
        .name("cust 2")
        .addressLineOne("2 test street")
        .town("2 test town")
        .county("2 test county")
        .country("2 test country")
        .postcode("2 test postcode")
        .build();
    Customer customer3 = Customer.builder()
        .id(3)
        .name("cust 3")
        .addressLineOne("3 test street")
        .town("3 test town")
        .county("3 test county")
        .country("3 test country")
        .postcode("3 test postcode")
        .build();

    when(customerService.getAllCustomers()).thenReturn(List.of(
        customer1,
        customer2,
        customer3
    ));
    mockMvc
        .perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            content()
                .json(
                    "["
                        + "{ref: 1"
                        + ",name:\"cust 1\", "
                        + "addressLineOne:\"1 test street\", "
                        + "town:\"1 test town\", "
                        + "county:\"1 test county\", "
                        + "country:\"1 test country\", "
                        + "postcode:\"1 test postcode\""
                        + "},"
                        + "{ref: 2"
                        + ",name:\"cust 2\", "
                        + "addressLineOne:\"2 test street\", "
                        + "town:\"2 test town\", "
                        + "county:\"2 test county\", "
                        + "country:\"2 test country\", "
                        + "postcode:\"2 test postcode\""
                        + "},"
                        + "{ref: 3"
                        + ",name:\"cust 3\", "
                        + "addressLineOne:\"3 test street\", "
                        + "town:\"3 test town\", "
                        + "county:\"3 test county\", "
                        + "country:\"3 test country\", "
                        + "postcode:\"3 test postcode\""
                        + "}"
                        + "]"));
  }

  @Test
  public void testGetCustomerById() throws Exception {
    Customer customer = Customer.builder()
        .id(1)
        .name("cust 1")
        .addressLineOne("1 test street")
        .town("1 test town")
        .county("1 test county")
        .country("1 test country")
        .postcode("1 test postcode")
        .build();

    when(customerService.getCustomerById(1)).thenReturn(customer);
    mockMvc
        .perform(get("/customer/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            content()
                .json(
                    "{ref: 1"
                        + ",name:\"cust 1\", "
                        + "addressLineOne:\"1 test street\", "
                        + "town:\"1 test town\", "
                        + "county:\"1 test county\", "
                        + "country:\"1 test country\", "
                        + "postcode:\"1 test postcode\""
                        + "}"));
  }

  @Test
  public void testGetCustomerByIdButCustomerNotFound() throws Exception {
    when(customerService.getCustomerById(1)).thenReturn(null);
    mockMvc
        .perform(get("/customer/1"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testCreateCustomerSuccess() throws Exception {
    Customer customer = Customer.builder()
        .id(1)
        .name("cust 1")
        .addressLineOne("1 test street")
        .town("1 test town")
        .county("1 test county")
        .country("1 test country")
        .postcode("1 test postcode")
        .build();
    doNothing().when(customerService).createCustomer(customer);
    mockMvc.perform(post("/customer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(customer)))
        .andExpect(status().isCreated());

    verify(customerService, times(1)).createCustomer(customer);
  }


}
