package oc.projet10.bean;

public class WaitingLineRequest {

    private int waitingLineId;

    private int bookId;

    private int memberId;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getWaitingLineId() {
        return waitingLineId;
    }

    public void setWaitingLineId(int waitingLineId) {
        this.waitingLineId = waitingLineId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
