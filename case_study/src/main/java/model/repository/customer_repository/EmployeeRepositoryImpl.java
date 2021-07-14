package model.repository.customer_repository;

import model.bean.customer.CustomerType;
import model.repository.BaseRepository;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    BaseRepository baseRepository = new BaseRepository();

    @Override
    public List<CustomerType> findAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void addNewCustomerType(CustomerType customerType) {

    }

    @Override
    public List<CustomerType> search(String name) {
        return null;
    }
}
