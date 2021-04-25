package com.scb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {
    @JsonProperty("orders")
    @NotBlank @NotNull
    List<Long> orders;
}
