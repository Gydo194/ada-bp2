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
        import nl.gkosten.adainf.models.Nota;

public class NotaOverzicht {
    private final VBox container;
    private final TableView notaTable = new TableView();
    private ObservableList<Nota> nota = FXCollections.observableArrayList();

    public NotaOverzicht() {
        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);


        Text title = new Text("Nota's");
        container.getChildren().add(title);


        notaTable.setEditable(true);


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

        notaTable.getColumns().addAll(
                codeColumn,
                omschrijvingColumn,
                prijsColumn
        );

        updateData();

        container.getChildren().add(notaTable);


        GridPane formGrid = new GridPane();

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

            /*
            Nota nota = new Nota(code, prijs, omschrijving);

            try {
                DAOProvider.getNotaDAO().saveNota(nota);
            } catch(DatalayerException e) {
                ErrorDialogController.showError("Database Fout", "Er is iets misgegaan bij het opslaan.");
                e.printStackTrace();
            }
            */

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

    private void updateData() {

        try {
            nota = FXCollections.observableArrayList(
                    DAOProvider.getNotaDAO().getAllNotas()
            );
        } catch (DatalayerException e) {
            ErrorDialogController.showError("Database fout", "Er is een fout opgetreden tijdens het laden van nota.");
            e.printStackTrace();
        }

        notaTable.setItems(nota);

    }

    public Node getContent() {
        return container;
    }
}
