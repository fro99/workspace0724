package com.kh.example.gearrent;

import java.util.Set;

public class Laptop extends Device {
    public Laptop(String id, String name, Set<String> tags) {
        super(id, name, "Laptop", tags);
    }

    @Override
    public int getBorrowLimitDays() {
        return 14;
    }

    @Override
    public int calcLateFee(int overdueDays) {
        return 500 * Math.max(0, overdueDays);
    }
}