package com.shopstyle.ms_payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "installments", nullable = false)
    private Boolean installments;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToOne(mappedBy = "payment")
    private Installment installment;

}
