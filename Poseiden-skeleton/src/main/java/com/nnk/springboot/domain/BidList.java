package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid_list_id")
    private int bidListId;

    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Bid Quantity is mandatory")
    @Column(name = "bid_quantity")
    private double bidQuantity;

    @Column(name = "ask_quantity")
    private double askQuantity;

    @NotBlank(message = "Bid is mandatory")
    @Column(name = "bid")
    private double bid;

    @NotBlank(message = "Ask is mandatory")
    @Column(name = "ask")
    private double ask;

    @NotBlank(message = "Benchmark is mandatory")
    @Column(name = "benchmark")
    private String benchmark;

    @NotBlank(message = "Bid List Date is mandatory")
    @Column(name = "bid_list_date")
    private Timestamp bidListDate;

    @NotBlank(message = "Commentary is mandatory")
    @Column(name = "commentary")
    private String commentary;

    @NotBlank(message = "Security is mandatory")
    @Column(name = "security")
    private String security;

    @NotBlank(message = "Status is mandatory")
    @Column(name = "status")
    private String status;

    @NotBlank(message = "Trader is mandatory")
    @Column(name = "trader")
    private String trader;

    @NotBlank(message = "Book is mandatory")
    @Column(name = "book")
    private String book;

    @NotBlank(message = "Creation Name is mandatory")
    @Column(name = "creation_name")
    private String creationName;

    @NotBlank(message = "Creation Date is mandatory")
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @NotBlank(message = "Revision Name is mandatory")
    @Column(name = "revision_name")
    private String revisionName;

    @NotBlank(message = "Revision Date is mandatory")
    @Column(name = "revision_date")
    private Timestamp revisionDate;

    @NotBlank(message = "Deal Name is mandatory")
    @Column(name = "deal_name")
    private String dealName;

    @NotBlank(message = "Deal Type is mandatory")
    @Column(name = "deal_type")
    private String dealType;

    @NotBlank(message = "Source List Id is mandatory")
    @Column(name = "source_list_id")
    private String sourceListId;

    @NotBlank(message = "Side is mandatory")
    @Column(name = "side")
    private String side;
}
