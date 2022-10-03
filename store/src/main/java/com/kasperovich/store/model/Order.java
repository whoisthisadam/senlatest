package com.kasperovich.store.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@Getter(PUBLIC)
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude = "products")
@FieldDefaults(level = PRIVATE, makeFinal = false)
@Entity
@Table(name="orders")
public class Order implements Comparator<Order>, Comparable<Order>{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column(name="user_id", nullable = false)
    Integer userId;

    @Column(name="status")
    String status;

    @Column(name="created_at")
    Timestamp createdAt;

    @ManyToMany
    @JoinTable(name = "products_orders",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    Set<Product>products;

    @Override
    public int compare(Order o1, Order o2) {
        return o1.getUserId().compareTo(o2.getUserId());
    }

    @Override
    public int compareTo(Order o) {
        return this.userId-o.userId;
    }


}
