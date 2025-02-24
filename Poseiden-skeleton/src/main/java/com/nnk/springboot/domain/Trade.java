package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trade_id")
    private int tradeId;

    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotBlank(message = "Buy Quantity is mandatory")
    @Column(name = "buy_quantity")
    private Double buyQuantity;

    @NotBlank(message = "Sell Quantity is mandatory")
    @Column(name = "sell_quantity")
    private Double sellQuantity;

    @NotBlank(message = "Buy Price is mandatory")
    @Column(name = "buy_price")
    private Double buyPrice;

    @NotBlank(message = "Sell Price is mandatory")
    @Column(name = "sell_price")
    private Double sellPrice;

    @NotBlank(message = "Trade Date is mandatory")
    @Column(name = "trade_date")
    private Timestamp tradeDate;

    @NotBlank(message = "Security is mandatory")
    @Column(name = "security")
    private String security;

    @NotBlank(message = "Status is mandatory")
    @Column(name = "status")
    private String status;

    @NotBlank(message = "Trader is mandatory")
    @Column(name = "trader")
    private String trader;

    @NotBlank(message = "Benchmark is mandatory")
    @Column(name = "benchmark")
    private String benchmark;

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
