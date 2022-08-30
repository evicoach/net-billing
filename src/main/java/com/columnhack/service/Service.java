package com.columnhack.service;

import com.columnhack.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    List<User> users;

    public Service() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void add(User user){
        users.add(user);
    }

    public void remove(long id){
       users = users.stream()
               .filter(user-> user.getId() != id)
               .collect(Collectors.toList());
    }
}
