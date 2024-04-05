package org.experis.wishlist;

import java.util.Objects;
//implementa Comparable interface per poter usare collections.sort
public class Present implements Comparable<Present>{
    String name;
    double price;

    public Present(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Present{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Present)) return false;
        Present present = (Present) o;
        return Double.compare(getPrice(), present.getPrice()) == 0 && Objects.equals(getName(), present.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }

    @Override
    public int compareTo(Present other) {
        return this.name.compareTo(other.name);
    }
}
