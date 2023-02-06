package nl.gkosten.adainf.views;

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
        import nl.gkosten.adainf.models.Behandelaar;
        import nl.gkosten.adainf.models.Geslacht;

        import java.util.Date;

public class BehandelarenOverzicht {
    private final VBox container;
    private final TableView behandelarenTable = new TableView();
    private ObservableList<Behandelaar> behandelaren = FXCollections.observableArrayList();

    public BehandelarenOverzicht() {
        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);


        Text title = new Text("Behandelaren");
        container.getChildren().add(title);




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

        updateData();

        container.getChildren().add(behandelarenTable);


        GridPane formGrid = new GridPane();

        Label bsnLabel              = new Label("BSN-nummer:");
        Label achternaamLabel       = new Label("Achternaam:");
        Label voorlettersLabel      = new Label("Voorletters:");
        Label geboortedatumLabel    = new Label("Geboortedatum:");
        Label emailLabel            = new Label("E-mail:");
        Label geslachtLabel         = new Label("Geslacht:");
        Label agbcodeLabel          = new Label("AGB-code:");

        TextField bsnField = new TextField();
        TextField achternaamField = new TextField();
        TextField voorlettersField = new TextField();
        TextField geboortedatumField = new TextField();
        TextField emailField = new TextField();
        TextField geslachtField = new TextField();
        TextField agbcodeField = new TextField();


        Button addButton = new Button("Toevoegen");
        addButton.setOnAction(actionEvent -> {

            int bsn, agbcode;
            String achternaam, voorletters, email;
            Geslacht geslacht;
            Date geboortedatum;

            try {
                //prijs = Double.parseDouble(prijsField.getText());
            } catch (NumberFormatException e) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldig nummer in!");
                e.printStackTrace();

                return;
            }

            /*
            Behandelaar behandelaar = new Behandelaar(code, prijs, omschrijving);

            try {
                DAOProvider.getBehandelaarDAO().saveBehandelaar(behandelaar);
            } catch(DatalayerException e) {
                ErrorDialogController.showError("Database Fout", "Er is iets misgegaan bij het opslaan.");
                e.printStackTrace();
            }
            */

            updateData();

        });

        formGrid.add(bsnLabel,              0, 0);
        formGrid.add(bsnField,              1, 0);

        formGrid.add(achternaamLabel,       0, 1);
        formGrid.add(achternaamField,       1, 1);

        formGrid.add(voorlettersLabel,      0, 2);
        formGrid.add(voorlettersField,      1, 2);

        formGrid.add(geboortedatumLabel,    0, 3);
        formGrid.add(geboortedatumField,    1, 3);

        formGrid.add(emailLabel,            2, 0);
        formGrid.add(emailField,            3, 0);

        formGrid.add(geslachtLabel,         2, 1);
        formGrid.add(geslachtField,         3, 1);

        formGrid.add(agbcodeLabel,          2, 2);
        formGrid.add(agbcodeField,          3, 2);

        formGrid.add(addButton,             3, 3);

        container.getChildren().add(formGrid);

    }

    private void updateData() {

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

    public Node getContent() {
        return container;
    }
}
