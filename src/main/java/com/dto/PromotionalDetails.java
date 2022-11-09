package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionalDetails {

    @JsonProperty("name")
    private String name;
    @JsonProperty("products")
    private List<String> products;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("promotionalPrice")
    private Double promotionalPrice;

}
