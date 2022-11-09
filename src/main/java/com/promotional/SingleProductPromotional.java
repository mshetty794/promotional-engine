package com.promotional;

import com.dto.Cart;

public class SingleProductPromotional implements Promotional{
    @Override
    public boolean isApplicable(Cart cartDetails) {
        return false;
    }

    @Override
    public double getDiscountPrice() {
        return 0.0;
    }
}
