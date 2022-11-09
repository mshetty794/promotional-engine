package com.engine;

import com.dto.Cart;
import com.dto.Product;
import com.promotional.Promotional;

import java.util.Map;

public class PromotionalEngineImpl implements PromotionalEngine {

    Map<String, Product> productCatalogue;

    public PromotionalEngineImpl(Map<String, Product> productCatalogue) {
        this.productCatalogue = productCatalogue;
    }

    @Override
    public double getCartPriceWithPromotionalApplied(Cart cartDetails) {
        cartDetails.setCheckOutAmount(getCheckOutPrice(cartDetails));
        for (Promotional promotional : cartDetails.getApplicablePromotional()) {
            if (promotional.isApplicable(cartDetails)) {
                double discountedPrice = applyPromotional(cartDetails, promotional);
                cartDetails.setPromotionalAmount(cartDetails.getPromotionalAmount() + discountedPrice);
            }
        }
        return cartDetails.getCheckOutAmount() - cartDetails.getPromotionalAmount();
    }

    private double applyPromotional(Cart cartDetails, Promotional promotional) {
        return promotional.getDiscountPrice(cartDetails);
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
