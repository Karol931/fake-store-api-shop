package shop;

public class Product {
    final private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageUrl;
    private ProductRating rating;

    Product(int id, String title, double price, String description, String category, String imageUrl, ProductRating rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductRating getRating() {
        return rating;
    }





    @Override
    public String toString() {
        return this.id + " " + this.title + " " + this.price + " " + this.description;
    }
}
