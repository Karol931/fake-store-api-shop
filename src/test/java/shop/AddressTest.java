package shop;

import org.junit.Test;
import static org.junit.Assert.*;

public class AddressTest {

    @Test
    public void testGetGeolocation() {
        Geolocation location = new Geolocation(51.5074, 0.1278);
        Address address = new Address(location, "London", "Baker Street", 221, "NW1 6XE");
        assertEquals(location, address.getGeolocation());
    }

    @Test
    public void testConstructor() {
        Geolocation location = new Geolocation(37.7749, -122.4194);
        Address address = new Address(location, "San Francisco", "Market Street", 1234, "94103");
        assertEquals(location, address.getGeolocation());
        assertEquals("San Francisco", address.getCity());
        assertEquals("Market Street", address.getStreet());
        assertEquals(1234, address.getStreetNumber());
        assertEquals("94103", address.getZipcode());
    }
}