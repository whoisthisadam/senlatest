package com.kasperovich.store.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kasperovich.store.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;


@Data
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(level = PRIVATE, makeFinal = false)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class OrderPOSTDto {

//    Long id;

    Integer userId;

    String status;

    Set<Long>productSet=new HashSet<>();

//    Timestamp createdAt;
}