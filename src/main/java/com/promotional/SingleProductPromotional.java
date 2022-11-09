package com.promotional;

import com.dto.Cart;

public class SingleProductPromotional implements Promotional {

    private int quantity;
    private String productName;
    private double promotionalPrice;

    public SingleProductPromotional(String productName, double promotionalPrice, int quantity) {
        this.quantity = quantity;
        this.productName = productName;
        this.promotionalPrice = promotionalPrice;
    }


    @Override
    public boolean isApplicable(Cart cartDetails) {
        return false;
    }

    @Override
    public double getDiscountPrice() {
        return 0.0;
    }
}
