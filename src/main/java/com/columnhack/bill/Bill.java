package com.columnhack.bill;

import com.columnhack.store.ItemType;

public class Bill {
    private long userId;
    private double amount;
    private ItemType itemsType;

    public ItemType getItemsType() {
        return itemsType;
    }

    public void setItemsType(ItemType itemsType) {
        this.itemsType = itemsType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
