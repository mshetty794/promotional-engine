package com.promotional;

import com.dto.Cart;
import com.dto.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CombinedProductPromotional implements Promotional {

    private List<String> productNames;
    private double promotionalPrice;

    public CombinedProductPromotional(List<String> productNames, double promotionalPrice) {
        this.productNames = productNames;
        this.promotionalPrice = promotionalPrice;
    }

    @Override
    public boolean isApplicable(Cart cartDetails) {
        List<String> promotional = new ArrayList<>();
        promotional.addAll(productNames);
        cartDetails.getOrderedItems().forEach((productName, quantity) -> {
            promotional.remove(productName);
        });
        if (promotional.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public double getDiscountPrice(Cart cartDetails, Map<String, Product> productCatalogue) {
        return 0.0;
    }
}
