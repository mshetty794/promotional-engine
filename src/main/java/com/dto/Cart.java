package com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    Map<String,Integer> orderedItems = new HashMap<>();
    private double checkOutAmount;
    private double promotionalAppliedAmount;
    private double payableAmount;
    List<PromotionalDetails> applicablePromotional;

}
