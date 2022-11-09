package com.configuration;

import com.dto.ProductCatalogue;
import com.dto.PromotionalDetails;
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
public class Configuration {

    @JsonProperty("products")
    private List<ProductCatalogue> productCatalogues;
    @JsonProperty("promotions")
    private List<PromotionalDetails> promotionDetails;

}
