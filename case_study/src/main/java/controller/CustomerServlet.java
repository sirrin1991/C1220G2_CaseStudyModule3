package controller;

import model.bean.customer.Customer;
import model.bean.customer.CustomerType;
import model.service.CustomerTypeService.CustomerTypeService;
import model.service.customer.CustomerService;
import model.service.customer.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustomerServlet",urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    CustomerTypeService customerTypeService = new CustomerTypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }

        switch (action){
            case "create":
                createCustomer(request,response);
                listCustomer(request,response);
                break;
            case "update":
                update(request,response);
                break;
            case "search":
                String code = request.getParameter("code"); // ""
                String name = request.getParameter("name");
                String address = request.getParameter("address"); // ""
                List<Customer> cusList = customerService.search(code,name,address);
                request.setAttribute("cusList",cusList);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customer.jsp");
                try {
                    requestDispatcher.forward(request,response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                listCustomer(request,response);
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(request.getParameter("id")));
        customer.setCustomerCode(request.getParameter("code"));
        customer.setName(request.getParameter("name"));
        String[] temp = request.getParameter("birthday").split("-");
        String birthday = String.join("-",temp[2],temp[1],temp[0]);
        customer.setBirthday(birthday);
        customer.setGender(Boolean.parseBoolean(request.getParameter("gender")));
        customer.setIdCard(request.getParameter("idCard"));
        customer.setPhone(request.getParameter("phone"));
        customer.setEmail(request.getParameter("email"));
        customer.setAddress(request.getParameter("address"));
        customer.setTypeId(Integer.parseInt(request.getParameter("typeId")));
        Map<String,String> mapError = customerService.update(customer);
        List<CustomerType> listType = customerTypeService.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view_customer/update.jsp");
        if(mapError.isEmpty()){
            String result = "Successfully";
            request.setAttribute("result",result);
            request.setAttribute("listType",listType);
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }else {
            request.setAttribute("customer",customer);
            request.setAttribute("mapError",mapError);
            request.setAttribute("listType",listType);
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        String customerCode = request.getParameter("code");
        String name = request.getParameter("name");
        String[] temp = request.getParameter("birthday").split("-");
        String birthday = null;

        try{
            birthday = String.join("-",temp[2],temp[1],temp[0]);
        }catch (Exception e){
            e.printStackTrace();
        }

        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String typeId = request.getParameter("typeId");
        Customer customer = new Customer(customerCode,name,birthday,gender,idCard,phone,email,address,Integer.parseInt(typeId));
        Map<String,String> mapError = customerService.add(customer);
        List<CustomerType> listType = customerTypeService.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view_customer/create.jsp");
        if(mapError.isEmpty()){
            String result = "Successfully";
            request.setAttribute("result",result);
            request.setAttribute("listType",listType);
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }else {
            request.setAttribute("customer",customer);
            request.setAttribute("mapError",mapError);
            request.setAttribute("listType",listType);
            try {
                requestDispatcher.forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create" :
                showCreateForm(request,response);
                break;
            case "update":
                showUpdateForm(request,response);
                break;
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                customerService.delete(id);
                listCustomer(request,response);
                break;

            default:
                listCustomer(request,response);
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.findById(id);
        List<CustomerType> listType =customerTypeService.findAll();
        request.setAttribute("listType",listType);
        request.setAttribute("customer",customer);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view_customer/update.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listCustomer (HttpServletRequest request, HttpServletResponse response) {
        List<Customer> cusList = customerService.findAll();
        request.setAttribute("cusList",cusList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customer.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response){
        List<CustomerType> listType =customerTypeService.findAll();
        request.setAttribute("listType",listType);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view_customer/create.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}



