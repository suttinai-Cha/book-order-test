package com.scb.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue
    private Long orderId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "status")
    private String status;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createDate;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.REMOVE)
    @JoinColumn(name="id", nullable=false)
    private Account account;
}
