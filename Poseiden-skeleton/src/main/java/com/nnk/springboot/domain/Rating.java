package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "Id is mandatory")
    private int id;

    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
    private int orderNumber;
}
