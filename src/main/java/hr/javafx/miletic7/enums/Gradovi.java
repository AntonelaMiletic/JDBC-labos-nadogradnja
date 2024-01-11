package hr.javafx.miletic7.enums;

public enum Gradovi {
    ZAGREB("Zagreb", "10000"),
    RIJEKA("Rijeka", "51000"),
    OSIJEK("Osijek", "31000"),
    VARAZDIN("Varaždin", "42000");

    private String name;
    private String postalCode;

    Gradovi(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
