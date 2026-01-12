module spotPer {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens interfaceGrafica to javafx.fxml;
    opens interfaceGrafica.base to javafx.fxml;
    opens interfaceGrafica.base.header to javafx.fxml;
    opens interfaceGrafica.base.sideBar to javafx.fxml;
    opens interfaceGrafica.modelos to javafx.fxml;


    exports interfaceGrafica;
}
