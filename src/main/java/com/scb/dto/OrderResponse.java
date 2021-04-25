package com.scb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderResponse {
    @JsonProperty("price")
    Double price;

    public OrderResponse() {

    }

    public OrderResponse(Double price) {
        this.price = price;

    }
}
