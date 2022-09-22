package com.kasperovich.store.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Comparator;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@Getter(PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level = PRIVATE, makeFinal = false)
@Entity
@Table(name="orders")
public class Order implements Comparator<Order>, Comparable<Order>{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @Column(name="user_id", nullable = false)
    Integer userId;

    @Column(name="status")
    String status;

    @Column(name="created_at")
    Timestamp createdAt;

    @Override
    public int compare(Order o1, Order o2) {
        return o1.getUserId().compareTo(o2.getUserId());
    }

    @Override
    public int compareTo(Order o) {
        return this.userId-o.userId;
    }
}
