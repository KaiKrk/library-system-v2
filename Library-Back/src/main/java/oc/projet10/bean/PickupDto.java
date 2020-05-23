package oc.projet10.bean;

import oc.projet10.Entity.Book;
import oc.projet10.Entity.Member;
import oc.projet10.Entity.PickupList;

import java.sql.Timestamp;
import java.util.Date;

public class PickupDto {

    private Member member;

    private Book book;

    private Date expirationDate;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public PickupDto(PickupList pickupList) {
        this.member = pickupList.getMember();
        this.book = pickupList.getBook();
        this.expirationDate = pickupList.getExpirationDate();
    }
}
