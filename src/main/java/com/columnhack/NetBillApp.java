package com.columnhack;

import com.columnhack.bill.Bill;
import com.columnhack.discount.DiscountService;
import com.columnhack.factory.BillCalculatorFactory;
import com.columnhack.service.BillingService;
import com.columnhack.service.CustomerService;
import com.columnhack.store.ItemType;
import com.columnhack.store.Store;
import com.columnhack.user.User;
import com.columnhack.user.UserType;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class NetBillApp {
    public static void main(String[] args){

        CustomerService customerService = new CustomerService();
        DiscountService discountService = new DiscountService(new HashMap<>());
        discountService.getDiscounts().put(UserType.EMPLOYEE, 30.0);
        discountService.getDiscounts().put(UserType.AFFILIATE, 10.0);
        discountService.getDiscounts().put(UserType.TWO_YEARS, 5.0);
        discountService.getDiscounts().put(UserType.NON_PERCENT, 5.0);
        discountService.getDiscounts().put(UserType.NONE, 0.0);

        User user1 = new User(1L, "John", "Doe", UserType.AFFILIATE, new Date());
        User user2 = new User(2L, "Mary", "Ann", UserType.EMPLOYEE, new Date());

        User user3 = new User(3L, "Olatunji", "Chrome", UserType.EMPLOYEE, getPastDate(-4));

        customerService.addCustomer(user1);
        customerService.addCustomer(user2);
        customerService.addCustomer(user3);

        Store store = new Store(1L,customerService,  discountService);

        BillCalculatorFactory factory = new BillCalculatorFactory();
        BillingService billingService = new BillingService(factory);
        Bill groceryBill = new Bill();
        groceryBill.setUserId(1L);
        groceryBill.setAmount(90);
        groceryBill.setItemsType(ItemType.GROCERY);

        double result = billingService.calculateNetAmount(groceryBill, store);
        System.out.println("Net Payable amount for bill " + groceryBill.getAmount() + " is " + result);

        Bill noGroceryBill = new Bill();
        noGroceryBill.setUserId(2L);
        noGroceryBill.setAmount(90);
        noGroceryBill.setItemsType(ItemType.NON_GROCERY);
        result = billingService.calculateNetAmount(noGroceryBill, store);
        System.out.println("Net Payable amount for bill " + noGroceryBill.getAmount() + " is " + result);

        Bill faithfulCustomerBill = new Bill();
        faithfulCustomerBill.setUserId(3L);
        faithfulCustomerBill.setAmount(90);
        faithfulCustomerBill.setItemsType(ItemType.NON_GROCERY);
        result = billingService.calculateNetAmount(faithfulCustomerBill, store);
        System.out.println("Net Payable amount for bill " + faithfulCustomerBill.getAmount() + " is " + result);


        Bill severalHundredBill = new Bill();
        severalHundredBill.setUserId(3L);
        severalHundredBill.setAmount(500);
        severalHundredBill.setItemsType(ItemType.NON_GROCERY);
        result = billingService.calculateNetAmount(severalHundredBill, store);
        System.out.println("Net Payable amount for customer over two years and several hundreds " + severalHundredBill.getAmount() + " is " + result);
    }

    private static Date getPastDate(int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, value);
        return c.getTime();
    }
}
