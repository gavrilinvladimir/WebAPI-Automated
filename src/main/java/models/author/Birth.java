package models.author;

public class Birth{
    public String date;
    public String country;
    public String city;

    public String getDate() {
        return date;
    }

    public Birth setDate(String date) {
        this.date = date;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Birth setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Birth setCity(String city) {
        this.city = city;
        return this;
    }
}
