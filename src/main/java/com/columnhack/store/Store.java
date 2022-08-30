package com.columnhack.store;

import com.columnhack.discount.DiscountService;
import com.columnhack.service.CustomerService;

public class Store {
    private Long id;
    private CustomerService customerService;
    private DiscountService discountService;

    public Store(Long id,
                 CustomerService customerService,
                 DiscountService discountService
    ) {
        this.id = id;
        this.customerService = customerService;
        this.discountService = discountService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
