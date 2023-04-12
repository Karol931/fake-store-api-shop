package shop;




public class User {
    final private int id;
    private Name name;
    private String email;
    private String username;
    private String password;
    private Address address;
    private String phone;

    User(int id, Name name, String email, String username, String password, Address address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public Name getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
    @Override
    public String toString() {
        return name.getFirstName() + " " + name.getLastName() + " " + this.id;
    }
}

