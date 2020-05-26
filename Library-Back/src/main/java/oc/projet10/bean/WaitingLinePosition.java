package oc.projet10.bean;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.WaitingLine;

import java.time.LocalDateTime;

public class WaitingLinePosition {

    private int position;

    private int maxSize;

    private String closestReturnDate;

    public WaitingLinePosition(Book book, int position, String date) {
        this.position = position;
        this.maxSize = book.getMax_copies()*2;
        this.closestReturnDate = date;
    }

    public String getClosestReturnDate() {
        return closestReturnDate;
    }

    public void setClosestReturnDate(String closestReturnDate) {
        this.closestReturnDate = closestReturnDate;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public String toString() {
        return "WaitingLinePosition{" +
                "position=" + position +
                ", maxSize=" + maxSize +
                '}';
    }
}
