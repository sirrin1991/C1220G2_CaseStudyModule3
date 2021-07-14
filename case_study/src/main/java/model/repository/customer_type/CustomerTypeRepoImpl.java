package model.repository.customer_type;

import model.bean.customer.CustomerType;
import model.repository.BaseRepository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeRepoImpl implements CustomerTypeRepo {
    BaseRepository baseRepository = new BaseRepository();
    @Override
    public List<CustomerType> findAll() {
        List<CustomerType> list = new ArrayList<>();
        CustomerType customerType ;

        try {
            CallableStatement callableStatement = baseRepository.getConnection().prepareCall("call get_list_customer_type()");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                customerType = new CustomerType();
                customerType.setId(resultSet.getInt("customer_type_id"));
                customerType.setName(resultSet.getString("customer_type_name"));
                list.add(customerType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

}
