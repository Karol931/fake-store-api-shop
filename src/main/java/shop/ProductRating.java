package shop;

public class ProductRating {
    private double rate;
    private double count;

    ProductRating(double rate, double count) {
        this.rate = rate;
        this.count = count;
    }

    public double getRate() {
        return rate;
    }

    public double getCount() {
        return count;
    }
}
