package shop;

public class Address {
    private Geolocation geolocation;
    private String city;
    private String street;
    private int streetNumber;
    private String zipcode;


    Address(Geolocation geolocation, String city, String street, int streetNumber, String zipcode) {
        this.geolocation = geolocation;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipcode = zipcode;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getZipcode() {
        return zipcode;
    }
}
