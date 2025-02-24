package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Curve Id is mandatory")
    @Column(name = "curve_id")
    private int curveId;

    @NotBlank(message = "As Of Date is mandatory")
    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @NotBlank(message = "Term is mandatory")
    @Column(name = "term")
    private Double term;

    @NotBlank(message = "Value is mandatory")
    @Column(name = "value")
    private Double value;

    @NotBlank(message = "Creation Date is mandatory")
    @Column(name = "creation_date")
    private Timestamp creationDate;
}
