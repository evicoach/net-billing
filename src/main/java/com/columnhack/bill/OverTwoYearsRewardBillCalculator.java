package com.columnhack.bill;

import com.columnhack.store.Store;
import com.columnhack.user.User;
import com.columnhack.user.UserType;

import java.util.Map;

public class OverTwoYearsRewardBillCalculator implements BillCalculator {
    @Override
    public double getNetAmount(User user, Bill bill, Store store) {
        Map<UserType, Double> storeDiscounts = store.getDiscountService()
                .getDiscounts();
        double twoYearsDiscount = storeDiscounts.get(UserType.TWO_YEARS);
        return bill.getAmount() - ((twoYearsDiscount / 100) * bill.getAmount());
    }
}
