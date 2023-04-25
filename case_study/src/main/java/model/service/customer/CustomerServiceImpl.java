package model.service.customer;

import model.bean.customer.Customer;
import model.repository.customer_repository.CustomerRepository;
import model.repository.customer_repository.CustomerRepositoryImpl;
import model.service.common.Validation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Map<String,String> add(Customer customer) {
        Map<String,String> mapError = new HashMap<>();
        if (!Validation.validateNameOrAddress(customer.getName())){
            mapError.put("name","Invalid format . (ex : Abc Abc).");
        }

        if (!Validation.validateCustomerCode(customer.getCustomerCode())){
            mapError.put("code","Invalid format . (ex : KH-XXXX , X is a number 0->9)");
        }

        if (!Validation.validateIdCard(customer.getIdCard())){
            mapError.put("card","Invalid format (ID card has 9 or 12 number) ");
        }

        if (!Validation.validatePhone(customer.getPhone())){
            mapError.put("phone","Invalid format . (ex : 090xxxxxxx or 091xxxxxxx or (84)+90xxxxxxx or (84)+91xxxxxxx)");
        }

        if (!Validation.validateEmail(customer.getEmail())){
            mapError.put("email","Invalid format (ex abc@abc.abc)");
        }

        if (!Validation.validateNameOrAddress(customer.getAddress())){
            mapError.put("address","Invalid format . (ex : Abc Abc) ");
        }

        if(mapError.isEmpty()){
            if(!customerRepository.add(customer)){ //true => !true == false // false => !false == true
                mapError.put("error","Something wrong, try again");
            }
        }

        return mapError;
    }

    @Override
    public Map<String,String> update(Customer customer) {
        Map<String,String> mapError = new HashMap<>();
        if (!Validation.validateNameOrAddress(customer.getName())){
            mapError.put("name","Invalid format . (ex : Abc Abc) ");
        }

        if (!Validation.validateCustomerCode(customer.getCustomerCode())){
            mapError.put("code","Invalid format . (ex : KH-XXXX , X is a number 0->9)");
        }

        if (!Validation.validateIdCard(customer.getIdCard())){
            mapError.put("card","Invalid format (ID card has 9 or 12 number) ");
        }

        if (!Validation.validatePhone(customer.getPhone())){
            mapError.put("phone","Invalid format . (ex : 090xxxxxxx or 091xxxxxxx or (84)+90xxxxxxx or (84)+91xxxxxxx)");
        }

        if (!Validation.validateEmail(customer.getEmail())){
            mapError.put("email","Invalid format (ex abc@abc.abc)");
        }

        if (!Validation.validateNameOrAddress(customer.getAddress())){
            mapError.put("address","Invalid format . (ex : Abc Abc) ");
        }

        if(mapError.isEmpty()){
            if(!customerRepository.update(customer)){
                mapError.put("error","Something wrong or duplicate, try again");
            }
        }

        return mapError;

    }

    @Override
    public void delete(int id) {
        customerRepository.delete(id);
    }

    @Override
    public List<Customer> search(String code, String name, String address) {
        return  customerRepository.search(code,name,address);
    }


}
