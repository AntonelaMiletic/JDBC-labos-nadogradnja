package hr.javafx.miletic7.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Factory extends NamedEntity implements Serializable {
    private String name;
    private Address address;
    private Set<Item> items = new HashSet<>();
    private Long addressId;

    public Factory(String name, Address address, Set<Item> items) {
        super(name);
        this.name = name;
        this.address = address;
        this.items = items;
    }

    public Factory() {

    }

    public Factory(Long id, String name, Long addressId) {
        super(id, name);
        this.addressId=addressId;
    }

    public Factory(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Factory{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", items=" + items +
                '}';
    }
}