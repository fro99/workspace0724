package com.kh.example.gearrent;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private String itemId;
    private String memberId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;

    public Loan(String itemId, String memberId, LocalDate loanDate, LocalDate dueDate) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnedDate = null;
    }

    public boolean isOverdue(LocalDate today) {
        LocalDate base = (returnedDate != null) ? returnedDate : today;
        return base.isAfter(dueDate);
    }

    public int overdueDays(LocalDate today) {
        LocalDate base = (returnedDate != null) ? returnedDate : today;
        if (base.isAfter(dueDate)) {
            return (int) ChronoUnit.DAYS.between(dueDate, base);
        }
        return 0;
    }

    public String getItemId() { return itemId; }
    public String getMemberId() { return memberId; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnedDate() { return returnedDate; }
    public void setReturnedDate(LocalDate returnedDate) { this.returnedDate = returnedDate; }

    @Override
    public String toString() {
        return String.format("Loan[item=%s, member=%s, loan=%s, due=%s, ret=%s]",
                itemId, memberId, loanDate, dueDate,
                (returnedDate == null ? "미반납" : returnedDate.toString()));
    }
}