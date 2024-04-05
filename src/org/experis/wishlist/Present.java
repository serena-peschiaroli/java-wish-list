package org.experis.wishlist;

import java.util.Objects;
//implementa Comparable interface per poter usare collections.sort
public class Present implements Comparable<Present>{
    String name;
    double price;

    //costrutture
    public Present(String name, double price) {
        validateString(name, "name");
        validatePrice(price, "price");
        this.name = name;
        this.price = price;
    }
    //getters$ setters
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
    //override
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
    //compare to override
    @Override
    public int compareTo(Present other) {
        return this.name.compareTo(other.name);
    }

    //metodi

    //metodo che solleva un'eccezione se la stringa è null o empty
    private void validateString(String value, String field) throws IllegalArgumentException {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(field + " is null or empty");
        }
    }

    private void validatePrice(double value, String field) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException(field + " is less or equal than zero.");
        }
    }


}
