import com.dto.Cart;
import com.dto.Product;
import com.dto.PromotionalDetails;
import com.engine.PromotionalEngine;
import com.engine.PromotionalEngineImpl;
import com.promotional.CombinedProductPromotional;
import com.promotional.Promotional;
import com.promotional.SingleProductPromotional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionalEngineTest {

    private static PromotionalEngine promotionalEngine;
    private static Map<String, Product> productCatalogue;

    @BeforeAll
    public static void setup() {
        productCatalogue = new HashMap<>();
        promotionalEngine = new PromotionalEngineImpl(productCatalogue);
        productCatalogue.put("A", new Product("A",50.0));
        productCatalogue.put("B", new Product("A",30.0));
        productCatalogue.put("C", new Product("A",20.0));
        productCatalogue.put("D", new Product("A",15.0));
    }


    @Test
    public void testMultiplePromotionalAppliedOnCart() {
        Cart cartDetails = new  Cart();
        Map<String,Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 3);
        orderDetails.put("B", 5);
        orderDetails.put("C", 1);
        orderDetails.put("D", 1);
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional individualProductPromotionalA = new SingleProductPromotional("A",130.0,3);
        Promotional individualProductPromotionalB = new SingleProductPromotional("B",45.0,2);
        List<String> product = new ArrayList<>();
        product.add("C");
        product.add("D");
        Promotional bundlePromotional = new CombinedProductPromotional(product, 30.0);
        applicablePromotional.add(bundlePromotional);
        applicablePromotional.add(individualProductPromotionalA);
        applicablePromotional.add(individualProductPromotionalB);
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCartPriceWithPromotionalApplied(cartDetails);
        assertEquals(280.0, checkoutPrice);
    }

    @Test
    public void testWithNoApplicablePromotionalOnCart() {
        Cart cartDetails = new  Cart();
        Map<String,Integer> orderDetails = new HashMap<>();
        orderDetails.put("A",1);
        orderDetails.put("B",1);
        orderDetails.put("D",1);
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional individualProductPromotionalA = new SingleProductPromotional("A",130.0,3);
        Promotional individualProductPromotionalB = new SingleProductPromotional("B",45.0,2);
        applicablePromotional.add(individualProductPromotionalA);
        applicablePromotional.add(individualProductPromotionalB);
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
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional individualProductPromotionalA = new SingleProductPromotional("A",130.0,3);
        Promotional individualProductPromotionalB = new SingleProductPromotional("B",45.0,2);
        applicablePromotional.add(individualProductPromotionalA);
        applicablePromotional.add(individualProductPromotionalB);
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCartPriceWithPromotionalApplied(cartDetails);
        assertEquals(350.0, checkoutPrice);
    }

    @Test
    public void testWithCombinedPromotionalAppliedOnCartTwoTimes() {
        Cart cartDetails = new  Cart();
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("C", 2);
        orderDetails.put("D", 2);
        List<String> product = new ArrayList<>();
        product.add("C");
        product.add("D");
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional bundlePromotional = new CombinedProductPromotional(product, 30.0);
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
        Promotional individualProductPromotionalA = new SingleProductPromotional("A",130.0,3);
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
        Promotional individualProductPromotional = new SingleProductPromotional("A",130.0,3);
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
        List<Promotional> applicablePromotional = new ArrayList<>();
        Promotional individualProductPromotionalA = new SingleProductPromotional("A",130.0,3);
        Promotional individualProductPromotionalB = new SingleProductPromotional("B",45.0,2);
        applicablePromotional.add(individualProductPromotionalA);
        applicablePromotional.add(individualProductPromotionalB);
        cartDetails.setApplicablePromotional(applicablePromotional);
        cartDetails.setOrderedItems(orderDetails);
        Double checkoutPrice = promotionalEngine.getCheckOutPrice(cartDetails);
        assertEquals(80.0, checkoutPrice);
    }

}
