package com.kh.example.gearrent;

import java.time.LocalDate;
import java.util.*;

public class GearRentController {
    private HashMap<String, Device> catalog = new HashMap<>();
    private HashMap<String, Member> members = new HashMap<>();
    private HashMap<String, Loan> activeLoans = new HashMap<>();

    public boolean addDevice(Device device) {
        if (catalog.containsKey(device.getId())) return false;
        catalog.put(device.getId(), device);
        return true;
    }

    public boolean addMember(Member member) {
        if (members.containsKey(member.getId())) return false;
        members.put(member.getId(), member);
        return true;
    }

    public Loan borrow(String memberId, String itemId, LocalDate today) {
        if (!members.containsKey(memberId)) {
            System.out.println("회원이 존재하지 않습니다.");
            return null;
        }
        if (!catalog.containsKey(itemId)) {
            System.out.println("장비가 존재하지 않습니다.");
            return null;
        }
        if (activeLoans.containsKey(itemId)) {
            System.out.println("이미 대여 중인 장비입니다.");
            return null;
        }
        Device device = catalog.get(itemId);
        LocalDate due = today.plusDays(device.getBorrowLimitDays());
        Loan loan = new Loan(itemId, memberId, today, due);
        activeLoans.put(itemId, loan);
        device.increaseBorrowCount();
        return loan;
    }

    public int returnItem(String itemId, LocalDate today) {
        Loan loan = activeLoans.get(itemId);
        if (loan == null) {
            System.out.println("해당 장비의 대여 기록이 없습니다.");
            return -1;
        }
        loan.setReturnedDate(today);
        int overdue = loan.overdueDays(today);
        Device device = catalog.get(itemId);
        int fee = device.calcLateFee(overdue);
        activeLoans.remove(itemId);
        return fee;
    }

    public ArrayList<Device> findByTag(String tag) {
        ArrayList<Device> result = new ArrayList<>();
        if (tag == null) return result;
        for (Device d : catalog.values()) {
            if (d.hasTag(tag)) result.add(d);
        }
        return result;
    }

    public ArrayList<Device> findByKeyword(String keyword) {
        ArrayList<Device> result = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) return result;
        String lower = keyword.toLowerCase();
        for (Device d : catalog.values()) {
            if (d.getName().toLowerCase().contains(lower) ||
                d.getCategory().toLowerCase().contains(lower)) {
                result.add(d);
            }
        }
        return result;
    }

    public Collection<Device> getAllDevices() {
        return Collections.unmodifiableCollection(catalog.values());
    }

    public Collection<Loan> getActiveLoans() {
        return Collections.unmodifiableCollection(activeLoans.values());
    }
}