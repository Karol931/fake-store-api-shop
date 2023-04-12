package shop;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

    public static URL getConnection(String urlString) {
        try {
            URL url = new URL(urlString);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if(responseCode != 200) {
                throw new RuntimeException("HttpResponseConnection: " + responseCode);
            }else {
                return url;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static User[] getUsers(URL url) {
        try {
            StringBuilder userString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                userString.append(scanner.nextLine());
            }
            //System.out.println(userString);
            scanner.close();
            JSONParser parser = new JSONParser();
            JSONArray userArray = (JSONArray) parser.parse(String.valueOf(userString));

            User[] users = new User[userArray.size()];

            for(int i = 0; i < userArray.size(); i++) {
                JSONObject jsonUser = (JSONObject) userArray.get(i);
                JSONObject jsonName = (JSONObject) jsonUser.get("name");
                JSONObject jsonAddress = (JSONObject) jsonUser.get("address");
                JSONObject jsonGeolocation = (JSONObject) jsonAddress.get("geolocation");

                int id = Integer.valueOf(jsonUser.get("id").toString());
                String firstName = jsonName.get("firstname").toString();
                String lastName = jsonName.get("lastname").toString();
                Name name = new Name(firstName,lastName);
                String email = jsonUser.get("email").toString();
                String username = jsonUser.get("username").toString();
                String password = jsonUser.get("password").toString();
                double latitude = Double.valueOf(jsonGeolocation.get("lat").toString());
                double longtitude = Double.valueOf(jsonGeolocation.get("long").toString());
                String city = jsonAddress.get("city").toString();
                String street =  jsonAddress.get("street").toString();
                int streetNumber = Integer.valueOf(jsonAddress.get("number").toString());
                String zipCode = jsonAddress.get("zipcode").toString();
                String phone = jsonUser.get("phone").toString();

                Geolocation geolocation = new Geolocation(latitude,longtitude);
                Address address = new Address(geolocation,city,street,streetNumber,zipCode);

                users[i] = new User(id,name,email,username,password,address,phone);
                //System.out.println(users[i]);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Product[] getProducts(URL url) {
        try {
            StringBuilder productString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                productString.append(scanner.nextLine());
            }
            //System.out.println(productString);
            scanner.close();
            JSONParser parser = new JSONParser();
            JSONArray productArray = (JSONArray) parser.parse(String.valueOf(productString));

            Product[] products = new Product[productArray.size()];
            for(int i = 0; i < productArray.size(); i++) {
                JSONObject jsonProduct = (JSONObject) productArray.get(i);
                JSONObject jsonProductRating = (JSONObject) jsonProduct.get("rating");

                int id = Integer.valueOf(jsonProduct.get("id").toString());
                String title = jsonProduct.get("title").toString();
                double price = Double.valueOf(jsonProduct.get("price").toString());
                String description =  jsonProduct.get("description").toString();
                String category = jsonProduct.get("category").toString();
                String imgUrl = jsonProduct.get("image").toString();
                double rate = Double.valueOf(jsonProductRating.get("rate").toString());
                double ratesCount = Double.valueOf(jsonProductRating.get("count").toString());

                ProductRating productRating = new ProductRating(rate,ratesCount);

                products[i] = new Product(id, title,price,description,category, imgUrl, productRating);

                //System.out.println(products[i]);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cart[] getCarts(URL url) {
        try {
            StringBuilder cartString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                cartString.append(scanner.nextLine());
            }
            //System.out.println(productString);
            scanner.close();
            JSONParser parser = new JSONParser();
            JSONArray cartArray = (JSONArray) parser.parse(String.valueOf(cartString));

            Cart[] carts = new Cart[cartArray.size()];
            for(int i = 0; i < cartArray.size(); i++) {
                JSONObject jsonCart = (JSONObject) cartArray.get(i);
                JSONArray jsonCartProducts = (JSONArray) jsonCart.get("products");
                int[] productIds = new int[jsonCartProducts.size()];
                int[] quantieties = new int[jsonCartProducts.size()];
                for(int j = 0; j < jsonCartProducts.size();j++) {
                    JSONObject jsonProduct = (JSONObject) jsonCartProducts.get(j);
                    productIds[j] = Integer.valueOf(jsonProduct.get("productId").toString());
                    quantieties[j] = Integer.valueOf(jsonProduct.get("quantity").toString());
                }
                Date date = parseDate(jsonCart.get("date").toString());
                int id = Integer.valueOf(jsonCart.get("id").toString());
                int userId = Integer.valueOf(jsonCart.get("userId").toString());
                carts[i] = new Cart(id,userId,date,productIds,quantieties);

                //System.out.println(carts[i]);
            }
            return carts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = format.parse(dateString);
            System.out.println(date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static User[] furthestLivingUsers(User[] users) {
        double maxDistance = 0;
        User[] furthestLivingUsers = new User[2];
        for(int i = 0; i < users.length; i++) {
            for (int j = i + 1; j < users.length; j++) {
                Geolocation geo1 = users[i].getAddress().getGeolocation();
                Geolocation geo2 = users[j].getAddress().getGeolocation();
                double distance = getDistanceBetweenPoints(geo1.getLatitude(), geo1.getLongtitude(), geo2.getLatitude(), geo2.getLongtitude());
                if (distance > maxDistance) {
                    maxDistance = distance;
                    furthestLivingUsers[0] = users[i];
                    furthestLivingUsers[1] = users[j];
                }
                //System.out.println(distance);
            }
        }

        return furthestLivingUsers;
    }

    public static double getDistanceBetweenPoints(double lat1, double long1, double lat2, double long2) {
        double R = 6371e3;
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(long2 - long1);

        double a = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);

        double c = (double) 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = (R * c / 1000);
        return distance;
    }

    public static Map<String,Double> getCategoriesWithTotalValues(Product[] products) {
        Map<String, Double> productCategoriesWithTotalValues = new HashMap<>();
        for (Product product : products) {
            String category = product.getCategory();
            Double value = product.getPrice();
            if(productCategoriesWithTotalValues.containsKey(product.getCategory())) {
                value += productCategoriesWithTotalValues.get(product.getCategory());
                productCategoriesWithTotalValues.put(product.getCategory(),value);
            }
            else {
                productCategoriesWithTotalValues.put(product.getCategory(),value);
            }
        }
        //System.out.println(productCategoriesWithTotalValues);
        return productCategoriesWithTotalValues;
    }

    public static User getUserById(int id, User[] users) {
        for(User user : users){
            if(user.getId() == id)
                return user;
        }
        return null;
    }
    public static String getHighestCartValue(Product[] products, Cart[] carts, User[] users) {
        double highestValue = carts[0].getCartValue(products);
        Cart highestValueCart = carts[0];
        System.out.println(carts[0].getCartValue(products));
        for(int i = 1; i < carts.length; i++) {
            if(highestValue < carts[i].getCartValue(products)) {
                highestValueCart = carts[i];
                highestValue = carts[i].getCartValue(products);
            }
            System.out.println(carts[i].getCartValue(products));
        }
        User user = getUserById(highestValueCart.getUserId(),users);
        return "Highest cart value: " + highestValue + ", Owner: " + user.getName().getFirstName() + " " + user.getName().getLastName();
    }
    public static void main(String[] args) {

        URL usersUrl = getConnection("https://fakestoreapi.com/users");
        User[] users = getUsers(usersUrl);

        URL productsUrl = getConnection("https://fakestoreapi.com/products");
        Product[] products = getProducts(productsUrl);

        URL cartsUrl = getConnection("https://fakestoreapi.com/carts");
        Cart[] carts = getCarts(cartsUrl);

        User[] furthestLivingUsers = furthestLivingUsers(users);
        Map<String,Double> productCategoriesWithTotalValues = getCategoriesWithTotalValues(products);

        System.out.println(getHighestCartValue(products,carts,users));
    }
}