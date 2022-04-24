package pojo;

import java.util.Objects;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getSuite(), address.getSuite()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getZipcode(), address.getZipcode()) && Objects.equals(getGeo(), address.getGeo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getSuite(), getCity(), getZipcode(), getGeo());
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
