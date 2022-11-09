package com.engine;

import com.dto.Cart;
import com.dto.Product;

import java.util.Map;

public class PromotionalEngineImpl implements PromotionalEngine {

    Map<String, Product> productCatalogue;

    public PromotionalEngineImpl(Map<String, Product> productCatalogue) {
        this.productCatalogue = productCatalogue;
    }

    @Override
    public double getCartPriceWithPromotionalApplied(Cart cartDetails) {
        return 0.0;
    }


    @Override
    public double getCheckOutPrice(Cart cartDetails) {
        double totalPrice = 0.0;
        for (Map.Entry<String, Integer> entry : cartDetails.getOrderedItems().entrySet()) {
            totalPrice += entry.getValue() * productCatalogue.get(entry.getKey()).getPrice();
        }
        return totalPrice;
    }
}
