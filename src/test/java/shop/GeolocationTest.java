package shop;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GeolocationTest {

    @Test
    public void testGetLatitude() {
        Geolocation location = new Geolocation(51.5074, 0.1278);
        assertEquals(51.5074, location.getLatitude(), 0.0001);
    }

    @Test
    public void testGetLongitude() {
        Geolocation location = new Geolocation(51.5074, 0.1278);
        assertEquals(0.1278, location.getLongtitude(), 0.0001);
    }

    @Test
    public void testConstructor() {
        Geolocation location = new Geolocation(37.7749, -122.4194);
        assertEquals(37.7749, location.getLatitude(), 0.0001);
        assertEquals(-122.4194, location.getLongtitude(), 0.0001);
    }
}