package com.kasperovich.store.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kasperovich.store.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@EqualsAndHashCode(exclude = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = false)
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    @Column(name="name")
    String name;

    @Column(name="price")
    Integer price;

    @Column(name="product_status")
    @JsonIgnore
    ProductStatus productStatus;

    @Column(name="created_at")
    @JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Minsk")
    @JsonIgnore
    Timestamp createdAt;

    @Column(name="is_deleted", columnDefinition = "bool default false")
    @JsonIgnore
    Boolean isDeleted;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    Set<Order> orders;

}
