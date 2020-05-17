package oc.projet10.Entity;

import javax.persistence.*;

@Entity
@Table(name = "waiting_list")
public class WaitingList {

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

    @Column(name = "waiting_position")
    public int waitingPosition;

}
