package com.columnhack.service;

import com.columnhack.user.User;
import com.columnhack.user.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CustomerServiceTest extends Service {

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Add new customer")
    @Test
    void addCustomer() {
        // given
        User user = new User(1L, "John", "Doe",
                UserType.EMPLOYEE, new Date());

        // when
        CustomerService customerService = new CustomerService();
        customerService.addCustomer(user);

        // then
        assertEquals(customerService.getUsers().size(), 1);
    }

    @DisplayName("Remove an existing customer")
    @Test
    void removeCustomer() {
        // given
        User user = new User(1L, "John", "Doe",
                UserType.EMPLOYEE, new Date());
        CustomerService customerService = new CustomerService();
        customerService.addCustomer(user);

        // when
       customerService.removeCustomer(1L);

        // then
        assertEquals(customerService.getUsers().size(), 0);
    }
}