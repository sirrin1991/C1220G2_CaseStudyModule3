package model.repository.customer_repository;

import model.bean.customer.Customer;
import model.repository.BaseRepository;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    BaseRepository baseRepository = new BaseRepository();

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        Customer customer;
        try {
            CallableStatement callableStatement =
                    this.baseRepository.getConnection().prepareCall("call get_list_customer()");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(Integer.parseInt(resultSet.getString("customer_id")));
                customer.setCustomerCode(resultSet.getString("customer_code"));
                customer.setName(resultSet.getString("customer_name"));

                String[] temp = resultSet.getString("customer_birthday").split("-");
                String birthday = String.join("-", temp[2], temp[1], temp[0]);
                customer.setBirthday(birthday);
                customer.setGender(resultSet.getBoolean("customer_gender"));
                customer.setIdCard(resultSet.getString("customer_id_card"));
                customer.setPhone(resultSet.getString("customer_phone"));
                customer.setEmail(resultSet.getString("customer_email"));
                customer.setAddress(resultSet.getString("customer_address"));
                customer.setTypeName(resultSet.getString("customer_type_name"));
                list.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        CallableStatement callableStatement;
        try {
            callableStatement =
                    this.baseRepository.getConnection().prepareCall("call get_customer_by_id(?)");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            customer = new Customer();
            if (resultSet.next()) {
                customer.setId(resultSet.getInt("customer_id"));
                customer.setCustomerCode(resultSet.getString("customer_code"));
                customer.setName(resultSet.getString("customer_name"));
                String[] temp = resultSet.getString("customer_birthday").split("-");
                String birthday = String.join("-", temp[2], temp[1], temp[0]);
                customer.setBirthday(birthday);
                customer.setGender(resultSet.getBoolean("customer_gender"));
                customer.setIdCard(resultSet.getString("customer_id_card"));
                customer.setPhone(resultSet.getString("customer_phone"));
                customer.setEmail(resultSet.getString("customer_email"));
                customer.setAddress(resultSet.getString("customer_address"));
                customer.setTypeName(resultSet.getString("customer_type_name"));
                customer.setTypeId(resultSet.getInt("customer_type_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customer;
    }

    @Override
    public boolean add(Customer customer) {
        try {
            CallableStatement callableStatement =
                    this.baseRepository.getConnection().prepareCall("call insert_customer(?,?,?,?,?,?,?,?,?)");
            callableStatement.setString(1, customer.getCustomerCode());
            callableStatement.setString(2, customer.getName());
            callableStatement.setString(3, customer.getBirthday());
            callableStatement.setBoolean(4, customer.getGender());
            callableStatement.setString(5, customer.getIdCard());
            callableStatement.setString(6, customer.getPhone());
            callableStatement.setString(7, customer.getEmail());
            callableStatement.setString(8, customer.getAddress());
            callableStatement.setInt(9, customer.getTypeId());
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Customer customer) {
        try {
            CallableStatement callableStatement =
                    this.baseRepository.getConnection().prepareCall("call update_customer(?,?,?,?,?,?,?,?,?,?)");
            callableStatement.setInt(1, customer.getId());
            callableStatement.setString(2, customer.getCustomerCode());
            callableStatement.setString(3, customer.getName());
            callableStatement.setString(4, customer.getBirthday());
            callableStatement.setBoolean(5, customer.getGender());
            callableStatement.setString(6, customer.getIdCard());
            callableStatement.setString(7, customer.getPhone());
            callableStatement.setString(8, customer.getEmail());
            callableStatement.setString(9, customer.getAddress());
            callableStatement.setInt(10, customer.getTypeId());
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(int id) {
        CallableStatement callableStatement;
        try {
            callableStatement = baseRepository.getConnection().prepareCall("call delete_by_id(?)");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Customer> search(String code, String name, String address) {
        List<Customer> list = new ArrayList<>();
        Customer customer ;
        CallableStatement callableStatement;
        try {
            callableStatement = baseRepository.getConnection().prepareCall("call search(?,?,?)");
            callableStatement.setString(1,code);
            callableStatement.setString(2,name);
            callableStatement.setString(3,address);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(Integer.parseInt(resultSet.getString("customer_id")));
                customer.setCustomerCode(resultSet.getString("customer_code"));
                customer.setName(resultSet.getString("customer_name"));
                String[] temp = resultSet.getString("customer_birthday").split("-");
                String birthday = String.join("-", temp[2], temp[1], temp[0]);
                customer.setBirthday(birthday);
                customer.setGender(resultSet.getBoolean("customer_gender"));
                customer.setIdCard(resultSet.getString("customer_id_card"));
                customer.setPhone(resultSet.getString("customer_phone"));
                customer.setEmail(resultSet.getString("customer_email"));
                customer.setAddress(resultSet.getString("customer_address"));
                customer.setTypeName(resultSet.getString("customer_type_name"));
                list.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

}
