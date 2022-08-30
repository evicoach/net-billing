package com.columnhack.bill;

import com.columnhack.store.Store;
import com.columnhack.user.User;

public class GroceryBillCalculator implements BillCalculator {
    @Override
    public double getNetAmount(User user, Bill bill, Store store) {
        return bill.getAmount();
    }
}
