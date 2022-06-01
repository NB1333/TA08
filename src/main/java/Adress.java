import HashCoder.HashCoder;
import HashCoder.IHashCoder;

import java.util.Objects;

public class Adress implements Comparable<Adress> {
    int building;
    private String city;
    private String street;

    public Adress(String city, String street, int building) {
        this.city = city;
        this.street = street;
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return building == adress.building && Objects.equals(street, adress.street) && Objects.equals(city, adress.city);
    }

    @Override
    public int hashCode() {
        IHashCoder hashCoder = new HashCoder();

        int result = street != null ? hashCoder.getHashCode(street) : 7;
        result = 31 * result + (city != null ? hashCoder.getHashCode(city) : 7);
        result = 31 * result + building;
        return result;
    }

    @Override
    public int compareTo(Adress o) {
        if (street.compareTo(o.street) == 0) {
            if (city.compareTo(o.city) == 0) {
                if (building == o.building) {
                    return 0;
                }

                return Integer.compare(building, o.building);
            }

            return city.compareTo(o.city);
        }

        return street.compareTo(o.street);
    }
}
