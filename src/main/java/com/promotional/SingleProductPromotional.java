package com.promotional;

import com.dto.Cart;

import java.util.Map;

public class SingleProductPromotional implements Promotional {

    private int quantity;
    private String productName;
    private double promotionalPrice;

    public SingleProductPromotional(String productName, double promotionalPrice, int quantity) {
        this.quantity = quantity;
        this.productName = productName;
        this.promotionalPrice = promotionalPrice;
    }


    @Override
    public boolean isApplicable(Cart cartDetails) {
        for (Map.Entry<String, Integer> orderedProduct : cartDetails.getOrderedItems().entrySet()) {
            if (orderedProduct.getKey().equalsIgnoreCase(productName) && orderedProduct.getValue() >= quantity)
                return true;
        }
        return false;
    }

    @Override
    public double getDiscountPrice(Cart cartDetails) {
        return 0.0;
    }
}
