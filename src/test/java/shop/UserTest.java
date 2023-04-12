package shop;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testGetId() {
        User user = new User(1, new Name("John", "Doe"), "johndoe@example.com", "johndoe", "password123", null, "123-456-7890");
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetName() {
        Name name =  new Name("John", "Doe");
        User user = new User(1, name, "johndoe@example.com", "johndoe", "password123", null, "123-456-7890");
        assertEquals(name, user.getName());
    }

    @Test
    public void testGetEmail() {
        User user = new User(1, new Name("John", "Doe"), "johndoe@example.com", "johndoe", "password123", null, "123-456-7890");
        assertEquals("johndoe@example.com", user.getEmail());
    }

    @Test
    public void testGetUsername() {
        User user = new User(1, new Name("John", "Doe"), "johndoe@example.com", "johndoe", "password123", null, "123-456-7890");
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        User user = new User(1, new Name("John", "Doe"), "johndoe@example.com", "johndoe", "password123", null, "123-456-7890");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGetAddress() {
        Address address = new Address(null, "New York", "5th Avenue", 123, "10001");
        User user = new User(1, new Name("John", "Doe"), "johndoe@example.com", "johndoe", "password123", address, "123-456-7890");
        assertEquals(address, user.getAddress());
    }

    @Test
    public void testGetPhone() {
        User user = new User(1, new Name("John", "Doe"), "johndoe@example.com", "johndoe", "password123", null, "123-456-7890");
        assertEquals("123-456-7890", user.getPhone());
    }

    @Test
    public void testToString() {
        User user = new User(1, new Name("John", "Doe"), "johndoe@example.com", "johndoe", "password123", null, "123-456-7890");
        assertEquals("John Doe 1", user.toString());
    }
}