package oc.projet10.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "waiting_list")
public class WaitingLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    public Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    public Member member;

    @Column(name = "register_date")
    public LocalDateTime registeredDate;

    @Column(name = "status")
    public String status;

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

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WaitingLine{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + member +
                ", registeredDate=" + registeredDate +
                ", status='" + status + '\'' +
                '}';
    }

    public WaitingLine() {

    }

}
