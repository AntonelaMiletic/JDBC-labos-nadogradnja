package hr.javafx.miletic7.model;

import hr.javafx.miletic7.enums.Gradovi;

import java.io.Serializable;
import java.util.Optional;

/**
 * Predstavlja entitet adrese koja je definirana ulicom, kucnim brojem, gradom i postanskim brojem
 */
public class Address implements Serializable {
    private Long id;
    private String street;
    private String houseNumber;
    private Optional<Gradovi> city;
    /**
     * Inicijalizira objekte klase adresa
     *
     * @param street ulica
     * @param houseNumber kucni broj
     * @param city grad
     */

    public Address(String street, String houseNumber, Optional<Gradovi> city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    public Address(Long id, String street, String houseNumber, Optional<Gradovi> city) {
        this.id=id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    public Address() {

    }

    public static class AddressBuilder{
        private String street;
        private String houseNumber;
        private Optional<Gradovi> city;

        public AddressBuilder(String street) {
            this.street=street;
        }

        public AddressBuilder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder setCity(Optional<Gradovi> city) {
            this.city = city;
            return this;
        }

        public Address build() {
            return new Address(this.street, this.houseNumber, this.city);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Optional<Gradovi> getCity() {
        return city;
    }

    public void setCity(Optional<Gradovi> city) {
        this.city = city;
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