package hr.javafx.miletic7.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Store<T> extends NamedEntity implements Serializable {
    private String name;
    private String webAddress;
    public Set<T> items;

    public Store(String name, String webAddress, Set<T> items) {
        super(name);
        this.name = name;
        this.webAddress = webAddress;
        this.items = items;
    }

    public Store() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Set<T> getItems() {
        return this.items;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}