import com.dto.Cart;
import com.dto.Product;
import com.dto.PromotionalDetails;
import com.engine.PromotionalEngine;
import com.engine.PromotionalEngineImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionalEngineTest {

    private static PromotionalEngine promotionalEngine;
    private static List<PromotionalDetails> promotions;
    private static HashMap<Object, Object> productCatalogue;
    private static Cart cartDetails;

    @BeforeAll
    public static void setup() {
        promotionalEngine = new PromotionalEngineImpl();
        productCatalogue = new HashMap<>();
        productCatalogue.put("A", new Product("A",50.0));
        productCatalogue.put("B", new Product("A",30.0));
        productCatalogue.put("C", new Product("A",20.0));
        productCatalogue.put("D", new Product("A",15.0));
        cartDetails = new Cart();
    }

    @AfterAll
    public static void teardown() {
        cartDetails.getOrderedItems().clear();
    }

    @Test
    public void testMultiplePromotionalAppliedOnCart() {
        Map<String,Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 3);
        orderDetails.put("B", 5);
        orderDetails.put("C", 1);
        orderDetails.put("D", 1);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCheckOutPrice(cartDetails);
        assertEquals(280.0, checkoutPrice);
    }

    @Test
    public void testWithNoApplicablePromotionalOnCart() {
        Map<String,Integer> orderDetails = new HashMap<>();
        orderDetails.put("A",1);
        orderDetails.put("B",1);
        orderDetails.put("D",1);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCheckOutPrice(cartDetails);
        assertEquals(95.0, checkoutPrice);
    }

    @Test
    public void testWithSinglePromotionalAppliedOnCart() {
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 5);
        orderDetails.put("B", 5);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCheckOutPrice(cartDetails);
        assertEquals(370.0, checkoutPrice);
    }

    @Test
    public void testWithCombinedPromotionalAppliedOnCartTwoTimes() {
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("C", 2);
        orderDetails.put("D", 2);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCheckOutPrice(cartDetails);
        assertEquals(60.0, checkoutPrice);
    }

    @Test
    public void testWithSinglePromotionalAppliedOnCartTwoTimes() {
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 6);
        cartDetails.setOrderedItems(orderDetails);
        double checkoutPrice = promotionalEngine.getCheckOutPrice(cartDetails);
        assertEquals(260.0, checkoutPrice);
    }

    @Test
    public void testWithSinglePromotionAppliedOnCartTwoTimesAndOneWithoutPromotion() {
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 7);
        cartDetails.setOrderedItems(orderDetails);
        Double checkoutPrice = promotionalEngine.getCheckOutPrice(cartDetails);
        assertEquals(310.0, checkoutPrice);
    }

}
