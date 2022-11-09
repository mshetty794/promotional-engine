package com.engine;

import com.dto.Cart;

public interface PromotionalEngine {

    double getCartPriceWithoutPromotional(Cart cartDetails);
    double getCheckOutPrice(Cart cartDetails);
}
