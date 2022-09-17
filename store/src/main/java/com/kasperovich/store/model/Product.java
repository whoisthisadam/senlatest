package com.kasperovich.store.model;


import com.kasperovich.store.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.security.Timestamp;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@Getter(PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level = PRIVATE, makeFinal = false)
@Entity
@Table(name="orders")
public class Product {
    @Id
    Integer id;

    @Column(name="name")
    String name;

    @Column(name="price")
    Integer price;

    @Column(name="product_status")
    ProductStatus productStatus;

    @Column(name="created_at")
    Timestamp createdAt;
}
