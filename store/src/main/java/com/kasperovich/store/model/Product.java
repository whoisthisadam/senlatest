package com.kasperovich.store.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kasperovich.store.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.annotation.processing.Generated;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Random;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@Getter(PUBLIC)
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level = PRIVATE, makeFinal = false)
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="name")
    String name;

    @Column(name="price")
    Integer price;

    @Column(name="product_status")
    @GeneratedValue
    ProductStatus productStatus;

    @Column(name="created_at")
    @JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Minsk")
    Timestamp createdAt;

    @Column(name="is_deleted", columnDefinition = "bool default false", nullable = false)
    Boolean isDeleted;
}
