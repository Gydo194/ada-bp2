package nl.gkosten.adainf.views.detail;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.gkosten.adainf.App;
import nl.gkosten.adainf.controllers.ErrorDialogController;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandeling;
import nl.gkosten.adainf.views.Main;
import nl.gkosten.adainf.views.overzicht.BehandelingenOverzicht;

public class BehandelingenDetailOverzicht {
    private final Behandeling behandeling;
    private final VBox container;

    public BehandelingenDetailOverzicht(Behandeling behandeling) {
        this.behandeling = behandeling;

        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);
        container.setSpacing(App.VERTICAL_SPACING);
        container.getStyleClass().add("detail-content-area");

        Text title = new Text(String.format("Behandeling '%s'", behandeling.getCode()));
        title.getStyleClass().add("header-text");
        container.getChildren().add(title);
        VBox.setMargin(title, App.DEFAULT_INSETS);

        Label codeLabel = new Label("Behandelingscode:");
        codeLabel.getStyleClass().add("form-label");
        Label prijsLabel = new Label("Prijs:");
        prijsLabel.getStyleClass().add("form-label");
        Label omschrijvingLabel = new Label("Omschrijving:");
        omschrijvingLabel.getStyleClass().add("form-label");

        Label codeField = new Label(behandeling.getCode()); //code kan niet worden aangepast
        TextField prijsField = new TextField();
        TextField omschrijvingField = new TextField();

        Button updateButton = new Button("Opslaan");
        updateButton.getStyleClass().add("edit-button");
        Button deleteButton = new Button("Verwijderen");
        deleteButton.getStyleClass().add("delete-button");

        GridPane formGrid = new GridPane();
        formGrid.setPrefWidth(App.PREFERRED_DIMENSIONS_X);

        formGrid.add(codeLabel,             0, 0);
        formGrid.add(codeField,             1, 0);
        formGrid.add(prijsLabel,            0, 1);
        formGrid.add(prijsField,            1, 1);
        formGrid.add(omschrijvingLabel,     0, 2);
        formGrid.add(omschrijvingField,     1, 2);
        formGrid.add(updateButton,          0, 3);
        formGrid.add(deleteButton,          1, 3);


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





        codeField.setText(behandeling.getCode());
        prijsField.setText(String.format("%.02f", behandeling.getPrijs()));
        omschrijvingField.setText(behandeling.getOmschrijving());

        updateButton.setOnAction(actionEvent -> {
           String behandelingscode, omschrijving;
           double prijs;

           //haal uit bestaand object
           behandelingscode = behandeling.getCode();

           omschrijving = omschrijvingField.getText();
            if(omschrijving.isBlank()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een omschrijving in.");

                return;
            }

            try {
                prijs = Double.parseDouble(prijsField.getText());
            } catch (NumberFormatException e) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldige prijs in.");
                e.printStackTrace();

                return;
            }

            Behandeling updated = new Behandeling(behandelingscode, prijs, omschrijving);

            System.out.println("UPDATE:");
            System.out.println(updated);

            try {
                DAOProvider.getBehandelingDAO().updateBehandeling(updated);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets misgegaan bij het wijzigen.");
                e.printStackTrace();

                return;
            }

            ErrorDialogController.showDialog("Item gewijzigd", "De behandeling is gewijzigd.");
            BehandelingenOverzicht.updateData();

        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                DAOProvider.getBehandelingDAO().deleteBehandeling(behandeling);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets misgegaan bij het verwijderen.");
                e.printStackTrace();

                return;
            }

            ErrorDialogController.showDialog("Item verwijderd", "De behandeling is verwijderd.");
            BehandelingenOverzicht.updateData();
            Main.closeTab(getTitle());
            Main.activateTab("Behandelingen");
        });

    }

    public Node getContent() {
        return container;
    }

    public String getTitle() {
        return String.format("Behandeling %s", behandeling.getCode());
    }

}
