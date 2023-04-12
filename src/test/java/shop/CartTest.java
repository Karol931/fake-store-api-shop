package shop;

import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void testGetId() {
        Cart cart = new Cart(1, 1, new Date(), new int[]{1, 2}, new int[]{3, 4});
        assertEquals(1, cart.getId());
    }

    @Test
    public void testGetUserId() {
        Cart cart = new Cart(1, 1, new Date(), new int[]{1, 2}, new int[]{3, 4});
        assertEquals(1, cart.getUserId());
    }

    @Test
    public void testGetDate() {
        Date date = new Date();
        Cart cart = new Cart(1, 1, date, new int[]{1, 2}, new int[]{3, 4});
        assertEquals(date, cart.getDate());
    }

    @Test
    public void testGetProductIds() {
        Cart cart = new Cart(1, 1, new Date(), new int[]{1, 2}, new int[]{3, 4});
        assertArrayEquals(new int[]{1, 2}, cart.getProductIds());
    }

    @Test
    public void testGetQuantities() {
        Cart cart = new Cart(1, 1, new Date(), new int[]{1, 2}, new int[]{3, 4});
        assertArrayEquals(new int[]{3, 4}, cart.getQuantities());
    }

    @Test
    public void testGetCartValue() {
        // create products
        Product product1 = new Product(1, "Product 1", 10.0, "Description 1", "Category 1", "image1.jpg", new ProductRating(4.5, 100));
        Product product2 = new Product(2, "Product 2", 20.0, "Description 2", "Category 2", "image2.jpg", new ProductRating(4.0, 50));
        Product product3 = new Product(3, "Product 3", 30.0, "Description 3", "Category 3", "image3.jpg", new ProductRating(3.5, 75));

        int[] productIds = {1, 2, 3};
        int[] quantities = {2, 1, 3};
        Cart cart = new Cart(1, 1, new Date(), productIds, quantities);

        // calculate the expected total value of the cart
        double expectedTotalValue = 2 * product1.getPrice() + 1 * product2.getPrice() + 3 * product3.getPrice();

        // calculate the actual total value using the getCartValue() method
        double actualTotalValue = cart.getCartValue(new Product[] {product1, product2, product3});

        // check that the actual total value matches the expected total value
        assertEquals(expectedTotalValue, actualTotalValue, 0.001);
    }
}
