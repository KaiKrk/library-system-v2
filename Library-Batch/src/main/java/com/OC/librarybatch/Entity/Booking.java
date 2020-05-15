package com.OC.librarybatch.Entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Booking {

    private int id;

    private String memberEmail;

    private String bookName;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate borrowingDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate returnDate;

    private Boolean renewable;

    private String status;

    public Booking() {
    }

    public Booking(int id, String memberEmail, String bookName, LocalDate borrowingDate, LocalDate returnDate, Boolean renewable, String status) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.bookName = bookName;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.renewable = renewable;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getRenewable() {
        return renewable;
    }

    public void setRenewable(Boolean renewable) {
        this.renewable = renewable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", memberEmail='" + memberEmail + '\'' +
                ", bookName='" + bookName + '\'' +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", renewable=" + renewable +
                ", status='" + status + '\'' +
                '}';
    }
}
