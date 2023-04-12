package shop;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void testGetters() {
        // Create a Product object
        Product product = new Product(1, "Test Product", 10.0, "This is a test product.", "Test Category", "http://test.com/product.jpg", new ProductRating(4.5, 10));

        // Test the getters
        assertEquals(1, product.getId());
        assertEquals("Test Product", product.getTitle());
        assertEquals(10.0, product.getPrice(), 0.0);
        assertEquals("This is a test product.", product.getDescription());
        assertEquals("Test Category", product.getCategory());
        assertEquals("http://test.com/product.jpg", product.getImageUrl());
        assertEquals(4.5, product.getRating().getRate(), 0.0);
        assertEquals(10, product.getRating().getCount(), 0.0);
    }

    @Test
    public void testToString() {
        // Create a Product object
        Product product = new Product(1, "Test Product", 10.0, "This is a test product.", "Test Category", "http://test.com/product.jpg", new ProductRating(4.5, 10));

        // Test the toString method
        assertEquals("1 Test Product 10.0 This is a test product.", product.toString());
    }
}