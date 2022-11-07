package Part_1;

public class Trip{
    private int code;
    private String city_from;
    private String city_to;
    private double price;
    private int number_airline;
    public Trip(int code, String city_from, String city_to, double price,int number_airline){
        this.code = code;
        this.city_from = city_from;
        this.city_to = city_to;
        this.price = price;
        this.number_airline = number_airline;
    }

    @Override
    public String toString() {
        return "Id: " + code +"\n" +
                "Місто відправлення: " + city_from + "\n" +
                "Місто прибуття: " + city_to + "\n" +
                "Ціна квитка: " + price + "\n";
    }

    public void setCode(int code) {
        this.code = code;
    }
    public void setCity_from(String city_from) {
        this.city_from = city_from;
    }
    public void setCity_to(String city_to) {
        this.city_to = city_to;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setNumber_airline(int number_airline) {
        this.number_airline = number_airline;
    }

    public int getCode() {
        return code;
    }
    public String getCity_from() {
        return city_from;
    }
    public String getCity_to() {
        return city_to;
    }
    public double getPrice() {
        return price;
    }
    public int getNumber_airline() {
        return number_airline;
    }
}
