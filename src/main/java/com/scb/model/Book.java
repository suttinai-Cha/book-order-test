package com.scb.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Book {
    @Id
    private Long id;
    @Column(name = "price")
    private Double price;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "is_recommended")
    private Boolean isRecommended;

}
