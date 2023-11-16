package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.bankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bms")
public class BMScontroller {

    List<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public List<Customer> getCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return new ApiResponse("Customer added",200);
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer){
       if(index== -1 || index > customers.size()-1){
           return new ApiResponse("not found",404);
       }else {
           customers.set(index,customer);
           return new ApiResponse("Customer updated",200);
       }

    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCumstomer(@PathVariable int index){
        if(index== -1 || index > customers.size()-1){
            return new ApiResponse("not found",404);
        }else {
            customers.remove(index);
            return new ApiResponse("Customer deleted",200);
        }

    }

    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index, @PathVariable double amount){
        if(index== -1 || index > customers.size()-1){
            return new ApiResponse("not found",404);
        }else {
            Customer customer1 = customers.get(index);
            customer1.setBalance(customer1.getBalance()+amount);
            return new ApiResponse("deposited",200);
        }


    }
    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdraw(@PathVariable int index, @PathVariable double amount){

        if(index== -1 || index > customers.size()-1){
            return new ApiResponse("not found",404);
        }else {
            Customer customer1 = customers.get(index);
            if(customer1.getBalance()<amount) {
                return new ApiResponse("money is not enough",404);
            }
            else {
                customer1.setBalance(customer1.getBalance()-amount);
                return new ApiResponse("withdrew",200);

            }
        }



    }

}
