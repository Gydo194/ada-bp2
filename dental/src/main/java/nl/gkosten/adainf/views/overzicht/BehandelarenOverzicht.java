package nl.gkosten.adainf.views.overzicht;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.gkosten.adainf.App;
import nl.gkosten.adainf.controllers.ErrorDialogController;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandelaar;
import nl.gkosten.adainf.models.Geslacht;
import nl.gkosten.adainf.views.Main;
import nl.gkosten.adainf.views.detail.BehandelarenDetailOverzicht;

import java.time.ZoneId;
import java.util.Date;

public class BehandelarenOverzicht {
    private static final VBox container = new VBox();
    private static final TableView<Behandelaar> behandelarenTable = new TableView();
    private static ObservableList<Behandelaar> behandelaren = FXCollections.observableArrayList();

    private BehandelarenOverzicht() {
        //verbied gebruik van constructor
    }

    //maak de view
    static {
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);
        container.setSpacing(App.VERTICAL_SPACING);
        container.getStyleClass().add("content-area");

        //header
        Text title = new Text("Behandelaren");
        title.getStyleClass().add("header-text");
        container.getChildren().add(title);
        VBox.setMargin(title, App.DEFAULT_INSETS);

        //tableview kolommen
        TableColumn bsnColumn = new TableColumn("BSN");
        bsnColumn.setCellValueFactory(
                new PropertyValueFactory<>("bsn")
        );

        TableColumn achternaamColumn = new TableColumn("Achternaam");
        achternaamColumn.setCellValueFactory(
                new PropertyValueFactory<>("achternaam")
        );

        TableColumn voorlettersColumn = new TableColumn("Voorletters");
        voorlettersColumn.setCellValueFactory(
                new PropertyValueFactory<>("voorletters")
        );

        TableColumn geboortedatumColumn = new TableColumn("Geboortedatum");
        geboortedatumColumn.setCellValueFactory(
                new PropertyValueFactory<>("geboortedatum")
        );

        TableColumn emailColumn = new TableColumn("E-mail");
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        TableColumn geslachtColumn = new TableColumn("Geslacht");
        geslachtColumn.setCellValueFactory(
                new PropertyValueFactory<>("geslacht")
        );

        TableColumn agbcodeColumn = new TableColumn("AGB-code");
        agbcodeColumn.setCellValueFactory(
                new PropertyValueFactory<>("agbcode")
        );

        behandelarenTable.getColumns().addAll(
                bsnColumn,
                achternaamColumn,
                voorlettersColumn,
                geboortedatumColumn,
                emailColumn,
                geslachtColumn,
                agbcodeColumn
        );
        VBox.setMargin(behandelarenTable, App.DEFAULT_INSETS);


        //open detailoverzicht bij dubbelklikken
        behandelarenTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() > 1) { //dubbel klik
                Behandelaar current = behandelarenTable.getSelectionModel().getSelectedItem();
                if(null != current) {
                    BehandelarenDetailOverzicht overzicht = new BehandelarenDetailOverzicht(current);
                    Main.addTab(overzicht.getContent(), overzicht.getTitle());
                }
            }
        });

        //vullen met data
        updateData();

        //tableview toevoegen
        container.getChildren().add(behandelarenTable);

        //form voor het aanmaken van nieuwe behandelaar
        GridPane formGrid = new GridPane();
        formGrid.setPrefWidth(App.PREFERRED_DIMENSIONS_Y);

        //labels
        Label bsnLabel              = new Label("BSN-nummer:");
        Label achternaamLabel       = new Label("Achternaam:");
        Label voorlettersLabel      = new Label("Voorletters:");
        Label geboortedatumLabel    = new Label("Geboortedatum:");
        Label emailLabel            = new Label("E-mail:");
        Label geslachtLabel         = new Label("Geslacht:");
        Label agbcodeLabel          = new Label("AGB-code:");

        bsnLabel.getStyleClass().add("form-label");
        achternaamLabel.getStyleClass().add("form-label");
        voorlettersLabel.getStyleClass().add("form-label");
        geboortedatumLabel.getStyleClass().add("form-label");
        emailLabel.getStyleClass().add("form-label");
        geslachtLabel.getStyleClass().add("form-label");
        agbcodeLabel.getStyleClass().add("form-label");

        //inputs
        TextField bsnField = new TextField();
        TextField achternaamField = new TextField();
        TextField voorlettersField = new TextField();
        TextField emailField = new TextField();
        TextField agbcodeField = new TextField();
        DatePicker geboortedatumPicker = new DatePicker();

        // geslacht dropdown
        ObservableList<String> geslachtBoxOptions = FXCollections.observableArrayList(
                "Man",
                "Vrouw"
        );
        ComboBox<String> geslachtBox = new ComboBox<>(geslachtBoxOptions);
        geslachtBox.getSelectionModel().selectLast();

        //toevoegen button
        Button addButton = new Button("Toevoegen");
        addButton.setOnAction(actionEvent -> {

            int bsn, agbcode;
            String achternaam, voorletters, email;
            Geslacht geslacht;
            Date geboortedatum;

            //inlezen en parsen van data uit form inputs, geef meldingen bij ongeldige input
            try {
                bsn = Integer.parseInt(bsnField.getText());
            } catch(NumberFormatException e) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldig BSN in!");
                e.printStackTrace();

                return;
            }

            try {
                agbcode = Integer.parseInt(agbcodeField.getText());
            } catch(NumberFormatException e) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldige AGB-code in!");
                e.printStackTrace();

                return;
            }

            achternaam = achternaamField.getText();
            if(achternaam.isBlank()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een achternaam in!");

                return;
            }


            voorletters = voorlettersField.getText();
            if(voorletters.isBlank()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer geldige voorletters in!");

                return;
            }

            email = emailField.getText();
            if(email.isBlank()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldig E-mail adres in!");

                return;
            }

            //geen validation nodig, alleen valid opties kunenn gekozen worden
            geslacht = Geslacht.from(geslachtBox.getValue());

            //datepicker value kan null zijn blijkbaar
            if(null == geboortedatumPicker.getValue()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldige datum in!");

                return;
            }

            //datepicker value van localdate naar date omzetten (aaaaaaaaaaaaaaaah)
            geboortedatum = Date.from(
                    geboortedatumPicker
                            .getValue()
                                .atStartOfDay(
                                        ZoneId.systemDefault()
                                ).toInstant()
            );

            //object maken
            Behandelaar behandelaar = new Behandelaar(
                    bsn,
                    achternaam,
                    voorletters,
                    geboortedatum,
                    email,
                    geslacht,
                    agbcode
            );

            //in database zetten
            try {
                DAOProvider.getBehandelaarDAO().saveBehandelaar(behandelaar);
            } catch(DatalayerException e) {
                ErrorDialogController.showError("Database Fout", "Er is iets misgegaan bij het opslaan.");
                e.printStackTrace();
            }

            //tableview data reload
            updateData();

        }); //eind button functie
        addButton.getStyleClass().add("add-button");

        //nieuwe behandelaar form grid
        formGrid.add(bsnLabel,              0, 0);
        formGrid.add(bsnField,              1, 0);

        formGrid.add(achternaamLabel,       0, 1);
        formGrid.add(achternaamField,       1, 1);

        formGrid.add(voorlettersLabel,      0, 2);
        formGrid.add(voorlettersField,      1, 2);

        formGrid.add(geboortedatumLabel,    0, 3);
        formGrid.add(geboortedatumPicker,   1, 3);

        formGrid.add(emailLabel,            2, 0);
        formGrid.add(emailField,            3, 0);

        formGrid.add(geslachtLabel,         2, 1);
        formGrid.add(geslachtBox,           3, 1);

        formGrid.add(agbcodeLabel,          2, 2);
        formGrid.add(agbcodeField,          3, 2);

        formGrid.add(addButton,             0, 4);

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

    }

    //vul tableview met data
    public static void updateData() {
        try {
            behandelaren = FXCollections.observableArrayList(
                    DAOProvider.getBehandelaarDAO().getAllBehandelaars()
            );
        } catch (DatalayerException e) {
            ErrorDialogController.showError("Database fout", "Er is een fout opgetreden tijdens het laden van behandelaren.");
            e.printStackTrace();
        }

        behandelarenTable.setItems(behandelaren);

    }

    //return de gegenereerde pane
    public static Node getContent() {
        return container;
    }
}
