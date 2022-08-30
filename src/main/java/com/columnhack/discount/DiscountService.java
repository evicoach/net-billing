package com.columnhack.discount;

import com.columnhack.user.UserType;

import java.util.Map;

public class DiscountService {
    Map<UserType, Double> discounts;

    public DiscountService(Map<UserType, Double> discounts) {
        this.discounts = discounts;
    }

    public Map<UserType, Double> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Map<UserType, Double> discounts) {
        this.discounts = discounts;
    }
}
