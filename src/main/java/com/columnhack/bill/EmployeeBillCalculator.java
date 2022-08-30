package com.columnhack.bill;

import com.columnhack.store.Store;
import com.columnhack.user.User;
import com.columnhack.user.UserType;

import java.util.Map;

public class EmployeeBillCalculator implements BillCalculator {
    @Override
    public double getNetAmount(User user, Bill bill, Store store) {
        Map<UserType, Double> storeDiscounts = store.getDiscountService()
                .getDiscounts();
        double discount = storeDiscounts.get(user.getType());
        return bill.getAmount() - ((discount / 100) * bill.getAmount());
    }
}
