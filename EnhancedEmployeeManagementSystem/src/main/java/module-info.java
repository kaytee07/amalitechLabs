module com.example.employeesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.example.employeesystem to javafx.fxml;
    exports com.example.employeesystem;
    exports com.example.employeesystem.Model;
    opens com.example.employeesystem.Model to javafx.fxml;
}