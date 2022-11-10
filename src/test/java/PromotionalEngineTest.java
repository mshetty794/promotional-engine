import com.dto.Cart;
import com.dto.Product;
import com.engine.PromotionalEngine;
import com.engine.PromotionalEngineImpl;
import com.promotional.CombinedProductPromotional;
import com.promotional.Promotional;
import com.promotional.SingleProductPromotional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionalEngineTest {

    private static PromotionalEngine promotionalEngine;

    @BeforeAll
    public static void setup() {
        Map<String, Product> productCatalogue = new HashMap<>();
        promotionalEngine = new PromotionalEngineImpl(productCatalogue);
        productCatalogue.put("A", new Product("A",50.0));
        productCatalogue.put("B", new Product("A",30.0));
        productCatalogue.put("C", new Product("A",20.0));
        productCatalogue.put("D", new Product("A",15.0));
    }


    @Test
    public void testMultiplePromotionalAppliedOnCart() {
        Cart cartDetails = new  Cart();
        Map<String, Integer> orderDetails = addProductToCartForMultiplePromotional();
        List<Promotional> applicablePromotional = getSingleAndCombinedProductPromotionalDetails();
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCartPriceWithPromotionalApplied(cartDetails);
        assertEquals(280.0, checkoutPrice);
    }

    private List<Promotional> getSingleAndCombinedProductPromotionalDetails() {
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional individualProductPromotionalA = getSinglePromotionalForProductA();
        Promotional individualProductPromotionalB = getSinglePromotionalForProductB();
        Promotional bundlePromotional = getCombinedPromotionalForProduct();
        applicablePromotional.add(individualProductPromotionalA);
        applicablePromotional.add(individualProductPromotionalB);
        applicablePromotional.add(bundlePromotional);
        return applicablePromotional;
    }

    private Promotional getSinglePromotionalForProductA() {
        return new SingleProductPromotional("A",130.0,3);
    }
    private Promotional getSinglePromotionalForProductB() {
        return new SingleProductPromotional("B",45.0,2);
    }
    private Promotional getCombinedPromotionalForProduct() {
        List<String> product = new ArrayList<>();
        product.add("C");
        product.add("D");
        return new CombinedProductPromotional(product, 30.0);
    }

    private Map<String, Integer> addProductToCartForMultiplePromotional() {
        Map<String,Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 3);
        orderDetails.put("B", 5);
        orderDetails.put("C", 1);
        orderDetails.put("D", 1);
        return orderDetails;
    }

    @Test
    public void testWithNoApplicablePromotionalOnCart() {
        Cart cartDetails = new  Cart();
        Map<String,Integer> orderDetails = new HashMap<>();
        orderDetails.put("A",1);
        orderDetails.put("B",1);
        orderDetails.put("D",1);
        List<Promotional> applicablePromotional = getPromotionalSingleProduct();
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCartPriceWithPromotionalApplied(cartDetails);
        assertEquals(95.0, checkoutPrice);
    }

    @Test
    public void testWithSinglePromotionalAppliedOnCart() {
        Cart cartDetails = new  Cart();
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 5);
        orderDetails.put("B", 5);
        orderDetails.put("C", 1);
        List<Promotional> applicablePromotional = getPromotionalSingleProduct();
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCartPriceWithPromotionalApplied(cartDetails);
        assertEquals(370.0, checkoutPrice);
    }

    @Test
    public void testWithCombinedPromotionalAppliedOnCartTwoTimes() {
        Cart cartDetails = new  Cart();
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("C", 2);
        orderDetails.put("D", 2);
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional bundlePromotional =  getCombinedPromotionalForProduct();
        applicablePromotional.add(bundlePromotional);
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCartPriceWithPromotionalApplied(cartDetails);
        assertEquals(60.0, checkoutPrice);
    }

    @Test
    public void testWithSinglePromotionalAppliedOnCartTwoTimes() {
        Cart cartDetails = new  Cart();
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 6);
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional individualProductPromotionalA = getSinglePromotionalForProductA();
        applicablePromotional.add(individualProductPromotionalA);
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCartPriceWithPromotionalApplied(cartDetails);
        assertEquals(260.0, checkoutPrice);
    }

    @Test
    public void testWithSinglePromotionAppliedOnCartTwoTimesAndOneWithoutPromotion() {
        Cart cartDetails = new  Cart();
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 7);
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional individualProductPromotional = getSinglePromotionalForProductA();
        applicablePromotional.add(individualProductPromotional);
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        Double checkoutPrice = promotionalEngine.getCartPriceWithPromotionalApplied(cartDetails);
        assertEquals(310.0, checkoutPrice);
    }

    @Test
    public void getCartPriceWithoutPromotionalApplied() {
        Cart cartDetails = new  Cart();
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 1);
        orderDetails.put("B", 1);
        List<Promotional> applicablePromotional = getPromotionalSingleProduct();
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        Double checkoutPrice = promotionalEngine.getCheckOutPrice(cartDetails);
        assertEquals(80.0, checkoutPrice);
    }

    private List<Promotional> getPromotionalSingleProduct() {
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional individualProductPromotionalA = new SingleProductPromotional("A",130.0,3);
        Promotional individualProductPromotionalB = new SingleProductPromotional("B",45.0,2);
        applicablePromotional.add(individualProductPromotionalA);
        applicablePromotional.add(individualProductPromotionalB);
        return applicablePromotional;
    }

}
