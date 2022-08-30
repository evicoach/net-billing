package com.columnhack.factory;

import com.columnhack.bill.*;
import com.columnhack.store.ItemType;
import com.columnhack.user.User;
import com.columnhack.user.UserType;

import java.util.Date;

public class BillCalculatorFactory {

    public BillCalculator newBillCalculator(User user, Bill bill) {
        // get userId from the bill;
        // get user with userId from the store user datastore
        // get the user type from the found user
        if (user.getDiffYears(new Date()) >= 2) {
            return new OverTwoYearsRewardBillCalculator();
        }
        if (bill.getItemsType() == ItemType.GROCERY) {
            return new GroceryBillCalculator();
        }
        if (user.getType() == UserType.AFFILIATE) {
            return new AffiliateBillCalculator();
        }
        return new EmployeeBillCalculator();
    }
}
