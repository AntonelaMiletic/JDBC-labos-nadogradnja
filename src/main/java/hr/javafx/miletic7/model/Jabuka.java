package hr.javafx.miletic7.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Jabuka extends Item implements Edible, Serializable {
    public static final Integer KILOCALORIES_PER_KILOGRAM=100;
    private BigDecimal weight; //kg

    public Jabuka(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal weight, Discount discount) {
        super(name, category, width, height, length, productionCost, sellingPrice, discount);
        this.weight = weight;
    }

    public Jabuka(BigDecimal weight) {
        this.weight = weight;
    }

    public Jabuka() {

    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public Integer calculateKilocalories() {
        return this.weight.multiply(BigDecimal.valueOf(KILOCALORIES_PER_KILOGRAM)).intValue();
    }

    @Override
    public BigDecimal calculatePrice() {
        return this.weight.multiply(super.getSellingPrice());
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
