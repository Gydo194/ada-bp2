package nl.gkosten.adainf.controllers;

import javafx.scene.control.Alert;

public class ErrorDialogController {
    //laat een foutmelding zien
    public static void showError(String header, String msg) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(msg);

        //wacht tot gesloten
        errorAlert.showAndWait();
    }

    //normale dialog
    public static void showDialog(String header, String msg) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(msg);

        //wacht tot gesloten
        errorAlert.showAndWait();
    }
}
