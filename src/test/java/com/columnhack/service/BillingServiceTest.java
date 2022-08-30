package com.columnhack.service;

import com.columnhack.bill.Bill;
import com.columnhack.bill.GroceryBillCalculator;
import com.columnhack.discount.DiscountService;
import com.columnhack.factory.BillCalculatorFactory;
import com.columnhack.store.ItemType;
import com.columnhack.store.Store;
import com.columnhack.user.User;
import com.columnhack.user.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.InstantSource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillingServiceTest {

    Store store;
    CustomerService customerService;
    DiscountService discountService;
    Map<UserType, Double> discounts;
    User user1;

    @Mock
    BillCalculatorFactory billCalculatorFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -2);
        user1 = new User(2L, "John", "Doe",
                UserType.EMPLOYEE, c.getTime());
        discounts = new HashMap<>();
        discounts.put(UserType.AFFILIATE, 10.0);
        discounts.put(UserType.EMPLOYEE, 30.0);
        discounts.put(UserType.NON_PERCENT, 5.0);
        discounts.put(UserType.TWO_YEARS, 5.0);
        discounts.put(UserType.NONE, 0.0);
        customerService = new CustomerService();
        customerService.addCustomer(new User(1L, "John", "Doe",
                UserType.AFFILIATE, new Date()));
        customerService.addCustomer(user1);
        discountService = new DiscountService(discounts);
        store = new Store(1L, customerService, discountService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateNetAmount() {
        BillCalculatorFactory factory = new BillCalculatorFactory();
        BillingService billingService = new BillingService(factory);
        Bill groceryBill = new Bill();
        groceryBill.setUserId(1L);
        groceryBill.setAmount(90);
        groceryBill.setItemsType(ItemType.NON_GROCERY);
        double result = billingService.calculateNetAmount(groceryBill, store);
        assertEquals(result, 81);
    }

    @Test
    void calculateGroceryBillNetAmount() {
        // given
        BillCalculatorFactory factory = new BillCalculatorFactory();
        BillingService billingService = new BillingService(factory);
        Bill groceryBill = new Bill();
        groceryBill.setUserId(1L);
        groceryBill.setAmount(390);
        groceryBill.setItemsType(ItemType.GROCERY);

        // when
        when(billCalculatorFactory.newBillCalculator(user1, groceryBill))
                .thenReturn(new GroceryBillCalculator());

        double result = billingService.calculateNetAmount(groceryBill, store);

        // then
        assertEquals(result, 375);
        verify(billCalculatorFactory, times(1));
    }
}