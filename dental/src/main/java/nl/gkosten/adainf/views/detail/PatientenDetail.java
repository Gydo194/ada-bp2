package nl.gkosten.adainf.views.detail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.gkosten.adainf.App;
import nl.gkosten.adainf.controllers.ErrorDialogController;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Patient;
import nl.gkosten.adainf.models.Geslacht;
import nl.gkosten.adainf.views.Main;
import nl.gkosten.adainf.views.overzicht.PatientenOverzicht;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PatientenDetail {
    private final Patient patient;
    private final VBox container;

    public PatientenDetail(Patient patient) {
        this.patient = patient;

        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);
        container.setSpacing(App.VERTICAL_SPACING);
        container.getStyleClass().add("detail-content-area");

        Text title = new Text(getTitle());
        title.getStyleClass().add("header-text");
        container.getChildren().add(title);
        VBox.setMargin(title, App.DEFAULT_INSETS);

        Label bsnLabel = new Label("BSN-nummer:");
        Label achternaamLabel = new Label("Achternaam:");
        Label voorlettersLabel = new Label("Voorletters:");
        Label geboortedatumLabel = new Label("Geboortedatum:");
        Label emailLabel = new Label("E-mail:");
        Label geslachtLabel = new Label("Geslacht:");
        Label relatienummerLabel = new Label("Relatienummer:");

        Label bsnField = new Label(String.format("%d", patient.getBsn())); //code kan niet worden aangepast
        TextField achternaamField = new TextField();
        TextField voorlettersField = new TextField();
        TextField emailField = new TextField();
        TextField relatienummerField = new TextField();
        DatePicker geboortedatumPicker = new DatePicker();

        // geslacht dropdown
        ObservableList<String> geslachtBoxOptions = FXCollections.observableArrayList(
                "Man",
                "Vrouw"
        );
        ComboBox<String> geslachtBox = new ComboBox<>(geslachtBoxOptions);
        geslachtBox.getSelectionModel().selectLast();







        Button updateButton = new Button("Opslaan");
        updateButton.setMaxWidth(Double.MAX_VALUE);
        updateButton.getStyleClass().add("edit-button");

        Button deleteButton = new Button("Verwijderen");
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.getStyleClass().add("delete-button");

        GridPane formGrid = new GridPane();
        formGrid.setPrefWidth(App.PREFERRED_DIMENSIONS_X);

        formGrid.add(bsnLabel,              0, 0);
        formGrid.add(bsnField,              1, 0);

        formGrid.add(achternaamLabel,       0, 1);
        formGrid.add(achternaamField,       1, 1);

        formGrid.add(voorlettersLabel,      0, 2);
        formGrid.add(voorlettersField,      1, 2);


        formGrid.add(geboortedatumLabel,    0, 3);
        formGrid.add(geboortedatumPicker,   1, 3);

        formGrid.add(emailLabel,            0, 4);
        formGrid.add(emailField,            1, 4);

        formGrid.add(geslachtLabel,         0, 5);
        formGrid.add(geslachtBox,           1, 5);

        formGrid.add(relatienummerLabel,          0, 6);
        formGrid.add(relatienummerField,          1, 6);

        formGrid.add(updateButton,          0, 7);
        formGrid.add(deleteButton,          1, 7);

        ColumnConstraints c0 = new ColumnConstraints();
        c0.setHgrow(Priority.ALWAYS);
        formGrid.getColumnConstraints().add(c0);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setHgrow(Priority.ALWAYS);
        formGrid.getColumnConstraints().add(c1);

        ColumnConstraints c2 = new ColumnConstraints();
        c2.setHgrow(Priority.ALWAYS);
        formGrid.getColumnConstraints().add(c2);

        ColumnConstraints c3 = new ColumnConstraints();
        c3.setHgrow(Priority.ALWAYS);
        formGrid.getColumnConstraints().add(c3);

        formGrid.setHgap(App.FORM_HGAP);
        formGrid.setVgap(App.FORM_VGAP);
        container.getChildren().add(formGrid);
        VBox.setMargin(formGrid, App.DEFAULT_INSETS);



        //populate inputs
        bsnField.setText(String.format("%d", patient.getBsn()));
        achternaamField.setText(patient.getAchternaam());
        voorlettersField.setText(patient.getVoorletters());
        emailField.setText(patient.getEmail());
        relatienummerField.setText(String.format("%d", patient.getRelatienummer()));
        geslachtBox.getSelectionModel().select(patient.getGeslacht().toString());
        geboortedatumPicker.setValue( //aaaaaaaaaaaaaaaaaaaaaaaa
                Instant.ofEpochMilli(
                                patient.getGeboortedatum()
                                        .getTime()).atZone(ZoneId.systemDefault())
                        .toLocalDate()
        );

        updateButton.setOnAction(actionEvent -> {

            int relatienummer;
            String achternaam, voorletters, email;
            Geslacht geslacht;
            Date geboortedatum;

            try {
                relatienummer = Integer.parseInt(relatienummerField.getText());
            } catch (NumberFormatException e) {
                ErrorDialogController.showDialog("Ongeldige Invoer", "Voer een geldig relatienummer in!");
                e.printStackTrace();

                return;
            }

            achternaam = achternaamField.getText();
            if(achternaam.isBlank()) {
                ErrorDialogController.showDialog("Ongeldige Invoer", "Voer een achternaam in!");

                return;
            }

            voorletters = voorlettersField.getText();
            if(voorletters.isBlank()) {
                ErrorDialogController.showDialog("Ongeldige Invoer", "Voer geldige voorletters in!");

                return;
            }

            email = emailField.getText();
            if(email.isBlank()) {
                ErrorDialogController.showDialog("Ongeldige Invoer", "Voer een E-mailadres in!");

                return;
            }

            geslacht = Geslacht.from(geslachtBox.getValue());


            if(null == geboortedatumPicker.getValue()) {
                ErrorDialogController.showDialog("Ongeldige Invoer", "Voer een geldige geboortedatum in!");

                return;
            }

            //aaaaaaaaaaaaaAAAAAAAAAAAHHHHHHHHHHHHHH
            geboortedatum = Date.from(
                    geboortedatumPicker
                            .getValue()
                            .atStartOfDay(
                                    ZoneId.systemDefault()
                            ).toInstant()
            );



            Patient updated = new Patient(
                    patient.getBsn(),
                    achternaam,
                    voorletters,
                    geboortedatum,
                    email,
                    geslacht,
                    relatienummer
            );

            try {
                DAOProvider.getPatientDAO().updatePatient(updated);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets misgegaan bij het wijzigen.");
                e.printStackTrace();

                return;
            }


            ErrorDialogController.showDialog("Item gewijzigd", "De patient is gewijzigd.");
            PatientenOverzicht.updateData();

        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                DAOProvider.getPatientDAO().deletePatient(patient);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets misgegaan bij het verwijderen.");
                e.printStackTrace();

                return;
            }

            ErrorDialogController.showDialog("Item verwijderd", "De patient is verwijderd.");
            PatientenOverzicht.updateData();
            Main.closeTab(getTitle());
            Main.activateTab("Patienten");
        });

    }

    public Node getContent() {
        return container;
    }

    public String getTitle() {
        return String.format("Patient %s, %s", patient.getAchternaam(), patient.getVoorletters());
    }

}
