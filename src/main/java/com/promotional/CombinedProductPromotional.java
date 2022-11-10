package com.promotional;

import com.dto.Cart;
import com.dto.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CombinedProductPromotional implements Promotional {

    private List<String> productNames;
    private double promotionalPrice;

    public CombinedProductPromotional(List<String> productNames, double promotionalPrice) {
        this.productNames = productNames;
        this.promotionalPrice = promotionalPrice;
    }

    @Override
    public boolean isApplicable(Cart cartDetails) {
        return checkIfApplicable(cartDetails);
    }

    private boolean checkIfApplicable(Cart cartDetails) {
        List<String> clonedPromotionalInfo = new ArrayList<>();
        clonedPromotionalInfo.addAll(productNames);
        cartDetails.getOrderedItems().forEach((productName, quantity) -> {
            clonedPromotionalInfo.remove(productName);
        });

        if (clonedPromotionalInfo.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public double getDiscountPrice(Cart cartDetails, Map<String, Product> productCatalogue) {

        double price = 0.0;
        int quantity = 0;

        List<String> clonedPromotionalInfo = new ArrayList<>();
        clonedPromotionalInfo.addAll(productNames);
        Map<String, Integer> clonedCartItems = getClonesCartDetails(cartDetails);
        List<String> productList = getProductList(clonedCartItems, new ArrayList<>());
        return getPriceAfterApplyingPromotional(productCatalogue, price, quantity, clonedPromotionalInfo, productList);
    }

    private double getPriceAfterApplyingPromotional(Map<String, Product> productCatalogue, double price, int quantity, List<String> clonedPromotionalInfo, List<String> productList) {
        int index = 0;
        for (String product : productList) {
            index += 1;
            price += getAggregatedPriceForProduct(productCatalogue, price, clonedPromotionalInfo, product);
            quantity = pairProductCombination(quantity, clonedPromotionalInfo);
            price = minusPriceOfLastUnpairedProduct(productCatalogue, price, productList, index, product);
        }
        return price - (quantity * promotionalPrice);
    }

    private double minusPriceOfLastUnpairedProduct(Map<String, Product> productCatalogue, double price, List<String> productList, int index, String product) {
        if (index == productList.size() && (index % productNames.size() != 0)) {
            price -= productCatalogue.get(product).getPrice();
        }
        return price;
    }

    private int pairProductCombination(int quantity, List<String> clonedPromotionalInfo) {
        if (clonedPromotionalInfo.size() == 0) {
            quantity += 1;
            clonedPromotionalInfo.addAll(productNames);
        }
        return quantity;
    }

    private double getAggregatedPriceForProduct(Map<String, Product> productCatalogue, double price, List<String> clonedPromotionalInfo, String product) {
        if (clonedPromotionalInfo.contains(product)) {
            clonedPromotionalInfo.remove(product);
            return productCatalogue.get(product).getPrice();
        }
        return 0.0;
    }

    private Map<String, Integer> getClonesCartDetails(Cart cartDetails) {
        Map<String, Integer> clonedCartItems = new ConcurrentHashMap<>();
        cartDetails.getOrderedItems().forEach((productId, quantities) -> {
            if (productNames.contains(productId)) {
                clonedCartItems.put(productId, quantities);
            }
        });
        return clonedCartItems;
    }

    private List<String> getProductList(Map<String, Integer> cartItems, ArrayList<String> productList) {

        if (cartItems.isEmpty()) {
            return productList;
        }
        cartItems.forEach((productId, quantities) -> {
            if (cartItems.get(productId) - 1 == 0) {
                cartItems.remove(productId);
            } else {
                cartItems.put(productId, (cartItems.get(productId) - 1));
            }
            productList.add(productId);
        });
        return getProductList(cartItems, productList);
    }

}
