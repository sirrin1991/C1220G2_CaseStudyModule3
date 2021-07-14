package model.service.CustomerTypeService;

import model.bean.customer.CustomerType;
import model.repository.customer_type.CustomerTypeRepo;
import model.repository.customer_type.CustomerTypeRepoImpl;

import java.util.List;

public class CustomerTypeService {
    CustomerTypeRepo customerTypeRepo = new CustomerTypeRepoImpl();


    public List<CustomerType> findAll(){
        return customerTypeRepo.findAll();
    }
}
