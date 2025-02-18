package com.nnk.springboot.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
    private int orderNumber;
}
