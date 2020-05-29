package oc.projet10.bean;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.Member;
import oc.projet10.Entity.PickupList;

import java.time.LocalDate;
public class PickupDto {

    private int id;

    private Member member;

    private Book book;

    private LocalDate expirationDate;


    public PickupDto(PickupList pickupList) {
        this.id = pickupList.getId();
        this.member = pickupList.getMember();
        this.book = pickupList.getBook();
        this.expirationDate = pickupList.getExpirationDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
