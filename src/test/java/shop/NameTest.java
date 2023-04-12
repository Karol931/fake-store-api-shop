package shop;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NameTest {

    @Test
    public void testGetName() {
        String firstName = "John";
        String lastName = "Doe";
        Name name = new Name(firstName, lastName);
        assertEquals(firstName, name.getFirstName());
        assertEquals(lastName, name.getLastName());
    }
    @Test
    public void testToString() {
        Name name = new Name("John", "Doe");
        assertEquals("John Doe", name.toString());
    }
}