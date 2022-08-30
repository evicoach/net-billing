package com.columnhack.service;

import com.columnhack.user.User;

public class CustomerService  extends Service {

    public void addCustomer(User customer){
        super.add(customer);
    }

    public void removeCustomer(long id){
        super.remove(id);
    }
}
