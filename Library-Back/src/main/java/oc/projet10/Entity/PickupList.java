package oc.projet10.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pickup_list")
public class PickupList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pickup_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    public Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    public Book book;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

}
