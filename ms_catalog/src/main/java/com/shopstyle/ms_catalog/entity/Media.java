package com.shopstyle.ms_catalog.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "sku_id", nullable = false)
    private Sku sku;
}
