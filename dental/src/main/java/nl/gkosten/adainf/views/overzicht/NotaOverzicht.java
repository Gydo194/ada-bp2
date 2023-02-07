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
        import nl.gkosten.adainf.models.Nota;
        import nl.gkosten.adainf.models.Patient;
        import nl.gkosten.adainf.views.Main;
        import nl.gkosten.adainf.views.detail.NotaDetail;

        import java.time.ZoneId;
        import java.util.Date;

public class NotaOverzicht {
    private static final VBox container;
    private static final TableView<Nota> notaTable = new TableView();
    private static ObservableList<Nota> nota = FXCollections.observableArrayList();

    private NotaOverzicht() {
    }

    static {
        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);


        Text title = new Text("Nota's");
        container.getChildren().add(title);


        notaTable.setEditable(true);


        TableColumn nummerColumn = new TableColumn("Notanummer");
        nummerColumn.setCellValueFactory(
                new PropertyValueFactory<>("nummer")
        );

        TableColumn patientColumn = new TableColumn("Patient");
        patientColumn.setCellValueFactory(
                new PropertyValueFactory<>("patient")
        );

        TableColumn behandelingColumn = new TableColumn("Behandeling");
        behandelingColumn.setCellValueFactory(
                new PropertyValueFactory<>("behandeling")
        );

        TableColumn startdatumColumn = new TableColumn("Startdatum");
        startdatumColumn.setCellValueFactory(
                new PropertyValueFactory<>("startdatum")
        );

        TableColumn einddatumColumn = new TableColumn("Einddatum");
        einddatumColumn.setCellValueFactory(
                new PropertyValueFactory<>("einddatum")
        );

        TableColumn prijsColumn = new TableColumn("Prijs");
        prijsColumn.setCellValueFactory(
                new PropertyValueFactory<>("prijs")
        );




        notaTable.getColumns().addAll(
                nummerColumn,
                patientColumn,
                behandelingColumn,
                startdatumColumn,
                einddatumColumn,
                prijsColumn
        );

        //open detailoverzicht bij dubbelklikken
        notaTable.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() > 1) { //dubbel klik
                Nota current = notaTable.getSelectionModel().getSelectedItem();
                if(null != current) {
                    NotaDetail overzicht = new NotaDetail(current);
                    Main.addTab(overzicht.getContent(), overzicht.getTitle());
                }
            }
        });

        updateData();

        container.getChildren().add(notaTable);


        GridPane formGrid = new GridPane();
        formGrid.setPrefWidth(App.PREFERRED_DIMENSIONS_Y);

        Label nummerLabel = new Label("Notanummer:");
        Label patientLabel = new Label("Patient Rel.nr:");
        Label behandelingLabel = new Label("Behandelingscode:");
        Label startdatumLabel = new Label("Startdatum:");
        Label einddatumLabel = new Label("Einddatum:");

        TextField nummerField = new TextField();
        TextField patientField = new TextField();
        TextField behandelingField = new TextField();

        DatePicker startdatumPicker = new DatePicker();
        DatePicker einddatumPicker = new DatePicker();






        Button addButton = new Button("Toevoegen");
        addButton.setOnAction(actionEvent -> {

            int notanummer, relatienummer;
            String behandelingscode;
            Date startdatum, einddatum;
            Patient patient;
            Behandeling behandeling;

            try {
                notanummer = Integer.parseInt(nummerField.getText());
            } catch(NumberFormatException e) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldig notanummer in!");
                e.printStackTrace();

                return;
            }


            try {
                relatienummer = Integer.parseInt(patientField.getText());
            } catch(NumberFormatException e) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldig relatienummer in!");
                e.printStackTrace();

                return;
            }

            behandelingscode = behandelingField.getText();
            if(behandelingscode.isBlank()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldige behandelingscode in!");

                return;
            }

            if(null == startdatumPicker.getValue()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een startdatum in!");

                return;
            }

            if(null == einddatumPicker.getValue()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een einddatum in!");

                return;
            }


            startdatum = Date.from(
                    startdatumPicker
                            .getValue()
                                .atStartOfDay(
                                        ZoneId.systemDefault()
                                ).toInstant()
            );

            einddatum = Date.from(
                    einddatumPicker
                            .getValue()
                                .atStartOfDay(
                                        ZoneId.systemDefault()
                                ).toInstant()
            );


            try {
                patient = DAOProvider.getPatientDAO().getPatient(relatienummer);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Patient kan niet worden gevonden of andere database fout. Controleer het relatienummer.");
                e.printStackTrace();

                return;
            }

            try {
                behandeling = DAOProvider.getBehandelingDAO().getBehandeling(behandelingscode);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Behandeling kan niet worden gevonden of andere database fout. Controleer de behandelingscode.");
                e.printStackTrace();

                return;
            }

            Nota nota = new Nota(
                    notanummer,
                    patient,
                    behandeling,
                    startdatum,
                    einddatum
            );

            try {
                DAOProvider.getNotaDAO().saveNota(nota);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets mis gegaan bij het opslaan.");
                e.printStackTrace();

                return;
            }

            updateData();

        });

        formGrid.add(nummerLabel,           0, 0);
        formGrid.add(nummerField,           1, 0);

        formGrid.add(patientLabel,          0, 1);
        formGrid.add(patientField,          1, 1);

        formGrid.add(behandelingLabel,      0, 2);
        formGrid.add(behandelingField,      1, 2);

        formGrid.add(startdatumLabel,       0, 3);
        formGrid.add(startdatumPicker,      1, 3);

        formGrid.add(einddatumLabel,        0, 4);
        formGrid.add(einddatumPicker,       1, 4);


        formGrid.add(addButton,             0, 5);

        container.getChildren().add(formGrid);

    }

    public static void updateData() {

        try {
            nota = FXCollections.observableArrayList(
                    DAOProvider.getNotaDAO().getAllNotas()
            );
        } catch (DatalayerException e) {
            ErrorDialogController.showError("Database fout", "Er is een fout opgetreden tijdens het laden van de nota's.");
            e.printStackTrace();
        }

        notaTable.setItems(nota);

    }

    public static Node getContent() {
        return container;
    }
}
