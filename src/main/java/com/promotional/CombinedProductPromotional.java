package com.promotional;

import com.dto.Cart;
import com.dto.Product;

import java.util.List;
import java.util.Map;

public class CombinedProductPromotional implements Promotional{

    private List<String> productNames;
    private double promotionalPrice;

    public CombinedProductPromotional(List<String> productNames, double promotionalPrice) {
        this.productNames = productNames;
        this.promotionalPrice = promotionalPrice;
    }

    @Override
    public boolean isApplicable(Cart cartDetails) {
        return false;
    }

    @Override
    public double getDiscountPrice(Cart cartDetails, Map<String, Product> productCatalogue) {
        return 0.0;
    }
}
