package nl.gkosten.adainf.views.overzicht;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.gkosten.adainf.App;
import nl.gkosten.adainf.controllers.ErrorDialogController;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandeling;
import nl.gkosten.adainf.views.Main;
import nl.gkosten.adainf.views.detail.BehandelingenDetailOverzicht;

public class BehandelingenOverzicht {
    private static final VBox container = new VBox();
    private static final TableView<Behandeling> behandelingenTable = new TableView<>();
    private static ObservableList<Behandeling> behandelingen = FXCollections.observableArrayList();

    private BehandelingenOverzicht() {
        //verbied gebruik van constructor
    }

    static {
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);


        Text title = new Text("Behandelingen");
        container.getChildren().add(title);


        behandelingenTable.setEditable(true);


        TableColumn codeColumn = new TableColumn("Code");
        codeColumn.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );

        TableColumn omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(
                new PropertyValueFactory<>("omschrijving")
        );

        TableColumn prijsColumn = new TableColumn("Prijs");
        prijsColumn.setCellValueFactory(
                new PropertyValueFactory<>("prijs")
        );

        //open detailoverzicht bij dubbelklikken
        behandelingenTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() > 1) { //dubbel klik
                Behandeling current = behandelingenTable.getSelectionModel().getSelectedItem();
                if(null != current) {
                    BehandelingenDetailOverzicht overzicht = new BehandelingenDetailOverzicht(current);
                    Main.addTab(overzicht.getContent(), overzicht.getTitle());
                }
            }
        });

        behandelingenTable.getColumns().addAll(
                codeColumn,
                omschrijvingColumn,
                prijsColumn
        );

        updateData();

        container.getChildren().add(behandelingenTable);


        GridPane formGrid = new GridPane();
        formGrid.setPrefWidth(App.PREFERRED_DIMENSIONS_Y);

        Label codeLabel = new Label("Code:");
        TextField codeField = new TextField();

        Label omschrijvingLabel = new Label("Omschrijving");
        TextField omschrijvingField = new TextField();

        Label prijsLabel = new Label("prijs");
        TextField prijsField = new TextField();

        Button addButton = new Button("Toevoegen");
        addButton.setOnAction(actionEvent -> {
            String code = codeField.getText();
            String omschrijving = omschrijvingField.getText();
            double prijs = 0.0D;

            try {
                prijs = Double.parseDouble(prijsField.getText());
            } catch (NumberFormatException e) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldig nummer in!");
                e.printStackTrace();

                return;
            }

            Behandeling behandeling = new Behandeling(code, prijs, omschrijving);

            try {
                DAOProvider.getBehandelingDAO().saveBehandeling(behandeling);
            } catch(DatalayerException e) {
                ErrorDialogController.showError("Database Fout", "Er is iets misgegaan bij het opslaan.");
                e.printStackTrace();
            }

            updateData();

        });

        formGrid.add(codeLabel,         0, 0);
        formGrid.add(codeField,         1, 0);
        formGrid.add(omschrijvingLabel, 0, 1);
        formGrid.add(omschrijvingField, 1, 1);
        formGrid.add(prijsLabel,        0, 2);
        formGrid.add(prijsField,        1, 2);
        formGrid.add(addButton,         0, 3);

        container.getChildren().add(formGrid);

    }

    public static void updateData() {

        try {
            behandelingen = FXCollections.observableArrayList(
                    DAOProvider.getBehandelingDAO().getAllBehandelingen()
            );
        } catch (DatalayerException e) {
            ErrorDialogController.showError("Database fout", "Er is een fout opgetreden tijdens het laden van behandelingen.");
            e.printStackTrace();
        }

        behandelingenTable.setItems(behandelingen);

    }

    public static Node getContent() {
        return container;
    }
}
