package com.promotional;

import com.dto.Cart;
import com.dto.Product;

import java.util.Map;

public interface Promotional {

    boolean isApplicable(Cart cartDetails);
    double getDiscountPrice(Cart cartDetails, Map<String, Product> productCatalogue);
}
