package com.promotional;

import com.dto.Cart;
import com.dto.Product;

import java.util.Map;

public class SingleProductPromotional implements Promotional {

    private final int quantity;
    private final String productName;
    private final double promotionalPrice;

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
    public double getDiscountPrice(Cart cartDetails, Map<String, Product> productCatalogue) {
        int multiplier = cartDetails.getOrderedItems().get(productName) / quantity;
        return ( quantity * multiplier * productCatalogue.get(productName).getPrice() ) - multiplier * promotionalPrice;
    }
}
