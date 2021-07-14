package model.repository.customer_repository;

import model.bean.customer.Customer;
import model.repository.Repository;

import java.util.List;

public interface CustomerRepository extends Repository<Customer> {
    List<Customer> findAll();
    Customer findById(int id);
    boolean add(Customer e);
    boolean update(Customer e);
    void delete(int id);
    List<Customer> search(String code , String name, String address);
}
