package oc.projet10.bean;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.Member;
import oc.projet10.Entity.WaitingLine;

import java.sql.Timestamp;

public class WaitingLineDto {

    private int id;

    private Book book;

    private Member member;

    private Timestamp registeredDate;

    private String status;

    public WaitingLineDto(WaitingLine waitingLine) {
        this.id = waitingLine.getId();
        this.book = waitingLine.getBook();
        this.member = waitingLine.getMember();
        this.registeredDate = waitingLine.getRegisteredDate();
        this.status = waitingLine.getStatus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Timestamp getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Timestamp registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
