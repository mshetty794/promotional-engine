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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PromotionalTest {

    private static PromotionalEngine promotionalEngine;
    private static List<PromotionalDetails> promotions;
    private static Cart cartDetails;
    private static Map<String, Product> productCatalogue;

    @BeforeAll
    public static void setup() {

        productCatalogue = new HashMap<>();
        promotionalEngine = new PromotionalEngineImpl(productCatalogue);
        //promotions = PromotionUtil.setupPromotions();
        productCatalogue.put("A", new Product("A", 50.0));
        productCatalogue.put("B", new Product("A", 30.0));
        productCatalogue.put("C", new Product("A", 20.0));
        productCatalogue.put("D", new Product("A", 15.0));
        cartDetails = new Cart();
    }

    @AfterAll
    public static void teardown() {
        cartDetails.getOrderedItems().clear();
    }

    @Test
    public void testIsApplicableOnSinglePromotionalTrue() {
        Promotional promotion = new SingleProductPromotional("A", 130.0, 3);
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("A", 3);
        cartDetails.setOrderedItems(orderDetails);
        assertTrue(promotion.isApplicable(cartDetails));
    }

    @Test
    public void testIsApplicableOnSinglePromotionalFalse() {
        Promotional promotion = new SingleProductPromotional("B", 45, 2);
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("B", 3);
        cartDetails.setOrderedItems(orderDetails);
        assertFalse(promotion.isApplicable(cartDetails));
    }

    @Test
    public void testIsApplicableOnCombinedPromotionalTrue() {
        List<String> product = new ArrayList<>();
        product.add("C");
        product.add("D");
        Promotional promotion = new CombinedProductPromotional(product, 30.0);
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("C", 1);
        orderDetails.put("D", 1);
        cartDetails.setOrderedItems(orderDetails);
        assertTrue(promotion.isApplicable(cartDetails));
    }

    @Test
    public void testIsApplicableOnCombinedPromotionalFalse() {
        List<String> product = new ArrayList<>();
        product.add("C");
        product.add("D");
        Promotional promotion = new CombinedProductPromotional(product, 30.0);
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("C", 1);
        cartDetails.setOrderedItems(orderDetails);
        assertTrue(promotion.isApplicable(cartDetails));
    }
}
