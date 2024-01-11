module hr.javafx.miletic7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.sql;

    opens hr.javafx.miletic7 to javafx.fxml;
    exports hr.javafx.miletic7;
}