package oc.projet10.bean;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.WaitingLine;

public class WaitingLinePosition {

    private int position;

    private int maxSize;

    public WaitingLinePosition( Book book, int postion) {
        this.position = postion;
        this.maxSize = book.getMax_copies()*2;
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
}
