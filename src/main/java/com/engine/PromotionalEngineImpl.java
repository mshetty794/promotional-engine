package com.engine;

import com.dto.Cart;

public class PromotionalEngineImpl implements PromotionalEngine {

    @Override
    public double getCartPriceWithoutPromotional(Cart cartDetails) {
        return 0;
    }

    @Override
    public double getCheckOutPrice(Cart cartDetails) {
        return 0;
    }
}
