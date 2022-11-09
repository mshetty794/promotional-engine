package com.dto;

import com.promotional.Promotional;
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
    private double promotionalAmount;
    private double payableAmount;
    List<Promotional> applicablePromotional;

}
