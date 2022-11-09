package com.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

    Map<String,Integer> orderedItems = new HashMap<>();
    private double checkOutAmount;
    private double promotionalAppliedAmount;
    private double payableAmount;
    List<PromotionalDetails> applicablePromotional;

}
