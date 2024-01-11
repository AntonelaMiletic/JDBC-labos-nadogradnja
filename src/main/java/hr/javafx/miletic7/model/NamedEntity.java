package hr.javafx.miletic7.model;

import java.io.Serializable;

public abstract class NamedEntity implements Serializable {
    private String name;
    private Long id;

    public NamedEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public NamedEntity(String name) {
        this.name = name;
    }

    public NamedEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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