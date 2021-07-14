package model.repository.customer_repository;

import model.bean.customer.CustomerType;

import java.util.List;

public interface EmployeeRepository {
    List<CustomerType> findAll();
    void deleteById ( int id);
    void addNewCustomerType(CustomerType customerType);
    List<CustomerType> search (String name);
}
