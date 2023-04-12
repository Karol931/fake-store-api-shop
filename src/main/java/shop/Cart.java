package shop;

import java.util.Date;

public class Cart {
    private int id;
    private int userId;
    private Date date;
    private int[] productIds;
    private int[] quantities;

    Cart(int id, int userId, Date date, int[] productIds, int[] quantities) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.productIds = new int[productIds.length];
        this.quantities = new int[quantities.length];
        for(int i = 0; i < productIds.length; i++) {
            this.productIds[i] = productIds[i];
            this.quantities[i] = quantities[i];
        }
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public int[] getProductIds() {
        return productIds;
    }

    public int[] getQuantities() {
        return quantities;
    }


    public double getCartValue(Product[] products) {
        int[] cartProductsIds = this.getProductIds();
        int[] cartProductsQuantities = this.getQuantities();
        double totalValue = 0;

        for(int i = 0; i < cartProductsIds.length; i++) {
            for(Product product : products) {
                if(product.getId() == cartProductsIds[i]) {
                    totalValue += product.getPrice() * cartProductsQuantities[i];
                }
            }
        }
        return totalValue;
    }

    @Override
    public String toString() {
        return this.id + " " + this.userId + " " + this.date;
    }
}
