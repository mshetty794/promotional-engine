package com.engine;

import com.dto.Cart;

public interface PromotionalEngine {

    double getCartPriceWithPromotionalApplied(Cart cartDetails);
    double getCheckOutPrice(Cart cartDetails);
}
