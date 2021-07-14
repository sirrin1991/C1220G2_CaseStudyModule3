package model.repository.customer_type;

import model.bean.customer.CustomerType;
import model.repository.Repository;

import java.util.List;

public interface CustomerTypeRepo extends Repository {
    List<CustomerType> findAll();
}
