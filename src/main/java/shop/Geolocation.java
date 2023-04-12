package shop;

public class Geolocation {
    private double latitude;
    private double longitude;

    Geolocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    double getLatitude(){
        return this.latitude;
    }

    double getLongtitude() {
        return this.longitude;
    }
}
