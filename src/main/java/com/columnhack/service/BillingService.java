package com.columnhack.service;

import com.columnhack.bill.Bill;
import com.columnhack.bill.BillCalculator;
import com.columnhack.factory.BillCalculatorFactory;
import com.columnhack.store.Store;
import com.columnhack.user.User;
import com.columnhack.user.UserType;

import java.util.Map;

public class BillingService {
    private BillCalculatorFactory billCalculatorFactory;

    public BillingService(BillCalculatorFactory billCalculatorFactory) {
        this.billCalculatorFactory = billCalculatorFactory;
    }

    public double calculateNetAmount(Bill bill, Store store) {
        double netAmount = 0.0;
        User customer = getCustomer(bill, store);
        BillCalculator billCalculator = billCalculatorFactory.newBillCalculator(customer, bill);
        netAmount = billCalculator.getNetAmount(customer, bill, store)
                - rewardForEveryHundred(bill, store.getDiscountService().getDiscounts());
        return netAmount;
    }

    private User getCustomer(Bill bill, Store store) {
        return store.getCustomerService().getUsers().stream()
                .filter(user -> user.getId() == bill.getUserId())
                .findFirst().get();
    }

    private double rewardForEveryHundred(Bill bill, Map<UserType, Double> discounts) {
        if (bill.getAmount() < 100) return 0.0;
        int noOfHundreds = (int) bill.getAmount() / 100;
        double nonPercentDiscount = discounts.get(UserType.NON_PERCENT);
        double totalDiscount = noOfHundreds * nonPercentDiscount;
        return totalDiscount;
    }
}
