package com.columnhack.bill;

import com.columnhack.store.Store;
import com.columnhack.user.User;

public interface BillCalculator {

    double getNetAmount(User user, Bill bill, Store store);
}
