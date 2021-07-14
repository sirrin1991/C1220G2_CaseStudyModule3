package model.service.customer;



import model.bean.customer.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    Map<String,String> add(Customer customer);
    Map<String,String> update(Customer customer);
    void delete(int id);
    List<Customer> search(String code,String name,String address);
}
