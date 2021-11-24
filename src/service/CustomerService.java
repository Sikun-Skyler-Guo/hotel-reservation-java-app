package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class CustomerService {
    Collection <Customer> customers = new ArrayList<>();

    private static CustomerService customerService = null;

    private CustomerService(){}

    public static CustomerService getInstance() {
        if(null ==customerService){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
    }

    public Customer getCustomer(String customerEmail){
        Optional<Customer> customer = customers.stream().filter(c -> customerEmail.equals(c.getEmail())).findFirst();
        return customer.orElse(null);
    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }
}
