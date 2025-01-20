module com.alpha.postockfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.alpha.postockfrontend to javafx.fxml;
    exports com.alpha.postockfrontend;
}