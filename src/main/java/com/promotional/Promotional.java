package com.promotional;

import com.dto.Cart;

public interface Promotional {

    boolean isApplicable(Cart cartDetails);
    double getDiscountPrice();
}
