package oc.projet10.bean;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.Member;
import oc.projet10.Entity.WaitingLine;

import java.time.LocalDate;

public class MemberWaitingLineDto {

    private int waitingLineId;

    private Book book;

    private Member member;

    private String category;

    private int placeInWaitingLine;

    private LocalDate closestReturnDate;

    public MemberWaitingLineDto(WaitingLine waitingLine, int placeInWaitingLine, LocalDate closestReturnDate) {
        this.waitingLineId = waitingLine.getId();
        this.book = waitingLine.getBook();
        this.member = waitingLine.getMember();
        this.category = waitingLine.getBook().getCategory();
        this.placeInWaitingLine = placeInWaitingLine;
        this.closestReturnDate = closestReturnDate;
    }

    public int getWaitingLineId() {
        return waitingLineId;
    }

    public void setWaitingLineId(int waitingLineId) {
        this.waitingLineId = waitingLineId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPlaceInWaitingLine() {
        return placeInWaitingLine;
    }

    public void setPlaceInWaitingLine(int placeInWaitingLine) {
        this.placeInWaitingLine = placeInWaitingLine;
    }

    public LocalDate getClosestReturnDate() {
        return closestReturnDate;
    }

    public void setClosestReturnDate(LocalDate closestReturnDate) {
        this.closestReturnDate = closestReturnDate;
    }
}
