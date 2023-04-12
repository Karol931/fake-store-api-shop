package shop;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductRatingTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testGetRate() {
        ProductRating rating = new ProductRating(4.5, 10);
        assertEquals(4.5, rating.getRate(), DELTA);
    }

    @Test
    public void testGetCount() {
        ProductRating rating = new ProductRating(4.5, 10);
        assertEquals(10, rating.getCount(), DELTA);
    }
}