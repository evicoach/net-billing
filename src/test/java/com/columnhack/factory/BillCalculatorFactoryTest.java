package com.columnhack.factory;

import com.columnhack.bill.*;
import com.columnhack.store.ItemType;
import com.columnhack.user.User;
import com.columnhack.user.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BillCalculatorFactoryTest {

    User user1;

    @BeforeEach
    void setUp() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        user1 = new User(2L, "John", "Doe",
                UserType.EMPLOYEE, c.getTime());
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Returns the Bill Calculator for Employee")
    @Test
    void newBillCalculator() {
        // given
        Bill groceryBill = new Bill();
        groceryBill.setUserId(1L);
        groceryBill.setAmount(90);
        groceryBill.setItemsType(ItemType.NON_GROCERY);

        // when
        BillCalculator billCalculator
                = new BillCalculatorFactory().newBillCalculator(user1, groceryBill);

        // then
        assertTrue(billCalculator instanceof EmployeeBillCalculator);
    }

    @DisplayName("Calculate bill for more than two years customers")
    @Test
    void moreThanTwoYearsBillCalculator() {
        // given
        Bill groceryBill = new Bill();
        groceryBill.setUserId(2L);
        groceryBill.setAmount(90);
        groceryBill.setItemsType(ItemType.NON_GROCERY);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -3);
        var user2 = new User(2L, "John", "Doe",
                UserType.EMPLOYEE, c.getTime());

        // when
        BillCalculator billCalculator
                = new BillCalculatorFactory().newBillCalculator(user2, groceryBill);

        // then
        assertTrue(billCalculator instanceof OverTwoYearsRewardBillCalculator);
    }

    @DisplayName("Should return GroceryBillCalculator")
    @Test
    void groceryBillCalculator() {
        // given
        Bill groceryBill = new Bill();
        groceryBill.setUserId(2L);
        groceryBill.setAmount(90);
        groceryBill.setItemsType(ItemType.GROCERY);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -3);
        var user2 = new User(2L, "John", "Doe",
                UserType.EMPLOYEE, c.getTime());

        // when
        BillCalculator billCalculator
                = new BillCalculatorFactory().newBillCalculator(user2, groceryBill);

        // then
        assertTrue(billCalculator instanceof GroceryBillCalculator);
    }
}