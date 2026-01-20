module ec.edu.espoch.queuecircularfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espoch.queuecircularfx to javafx.fxml;
    opens ec.edu.espoch.queuecircularfx.controller to javafx.fxml;
    exports ec.edu.espoch.queuecircularfx;
    exports ec.edu.espoch.queuecircularfx.controller;
    exports ec.edu.espoch.queuecircularfx.modelo;
    exports ec.edu.espoch.queuecircularfx.vista;
}   
