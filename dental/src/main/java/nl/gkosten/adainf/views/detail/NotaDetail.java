package nl.gkosten.adainf.views.detail;

        import javafx.scene.Node;
        import javafx.scene.control.Button;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
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
        import nl.gkosten.adainf.models.Persoon;
        import nl.gkosten.adainf.views.Main;
        import nl.gkosten.adainf.views.overzicht.BehandelingenOverzicht;
        import nl.gkosten.adainf.views.overzicht.NotaOverzicht;

        import java.time.Instant;
        import java.time.ZoneId;
        import java.util.Date;

public class NotaDetail {
    private final Nota nota;
    private final VBox container;

    public NotaDetail(Nota nota) {
        this.nota = nota;

        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);

        Text title = new Text(String.format("Nota '%s'", nota.getNummer()));
        container.getChildren().add(title);

        Label notanummerLabel = new Label("Notanummer:");
        Label patientLabel = new Label("Patient:");
        Label startdatumLabel = new Label("Startdatum:");
        Label einddatumLabel = new Label("Einddatum:");
        Label behandelingLabel = new Label("Behandeling:");
        Label prijsLabel = new Label("Prijs:");

        Label notanummerField = new Label(String.format("%d", nota.getNummer())); //notanummer kan niet worden aangepast
        TextField patientField = new TextField();
        DatePicker startdatumPicker = new DatePicker();
        DatePicker einddatumPicker = new DatePicker();
        TextField behandelingField = new TextField();
        Label prijsField = new Label(String.format("%.02f", nota.getBehandeling().getPrijs()));

        Button updateButton = new Button("Opslaan");
        Button deleteButton = new Button("Verwijderen");

        GridPane formGrid = new GridPane();
        formGrid.setPrefWidth(App.PREFERRED_DIMENSIONS_X);

        formGrid.add(notanummerLabel,      0, 0);
        formGrid.add(notanummerField,      1, 0);

        formGrid.add(patientLabel,         0, 1);
        formGrid.add(patientField,         1, 1);

        formGrid.add(startdatumLabel,      0, 2);
        formGrid.add(startdatumPicker,     1, 2);

        formGrid.add(einddatumLabel,       0, 3);
        formGrid.add(einddatumPicker,      1, 3);

        formGrid.add(behandelingLabel,     0, 4);
        formGrid.add(behandelingField,     1, 4);

        formGrid.add(prijsLabel,           0, 5);
        formGrid.add(prijsField,           1, 5);


        formGrid.add(updateButton,         0, 6);
        formGrid.add(deleteButton,         1, 6);

        container.getChildren().add(formGrid);


        //populate form inputs
        patientField.setText(
                String.format("%d",
                        nota.getPatient().getBsn())); //TODO BSN -> RELNR


        startdatumPicker.setValue(
                Instant.ofEpochMilli(
                            nota.getStartdatum()
                                        .getTime()).atZone(ZoneId.systemDefault())
                                                .toLocalDate()
        );

        einddatumPicker.setValue(
                Instant.ofEpochMilli(
                            nota.getEinddatum()
                                        .getTime()).atZone(ZoneId.systemDefault())
                                                .toLocalDate()
        );




        notanummerField.setText(String.format("%d", nota.getNummer()));
        prijsField.setText(String.format("€%.02f", nota.getPrijs()));
        behandelingField.setText(nota.getBehandeling().getCode());

        updateButton.setOnAction(actionEvent -> {

            int relatienummer;
            String behandelingscode;
            Date startdatum, einddatum;

            try {
                relatienummer = Integer.parseInt(patientField.getText());
            } catch (NumberFormatException e) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldig relatienummer in!");
                e.printStackTrace();

                return;
            }

            behandelingscode = behandelingField.getText();
            if(behandelingscode.isBlank()) {
                ErrorDialogController.showError("Ongeldige Invoer", "Voer een geldige behandelingscode in!");

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
                    Patient patient = DAOProvider.getPatientDAO().getPatient(relatienummer);
                    Behandeling behandeling = DAOProvider.getBehandelingDAO().getBehandeling(behandelingscode);

                    Nota updated = new Nota(
                            nota.getNummer(),
                            patient,
                            behandeling,
                            startdatum,
                            einddatum
                    );

                    DAOProvider.getNotaDAO().updateNota(updated);

            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets misgegaan bij het wijzigen.");
                e.printStackTrace();

                return;
            }

            ErrorDialogController.showDialog("Item gewijzigd", "De nota is gewijzigd.");
            NotaOverzicht.updateData();

        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                DAOProvider.getNotaDAO().deleteNota(nota);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets misgegaan bij het verwijderen.");
                e.printStackTrace();

                return;
            }

            ErrorDialogController.showDialog("Item verwijderd", "De nota is verwijderd.");
            NotaOverzicht.updateData();
            Main.closeTab(getTitle());
            Main.activateTab("Nota");
        });

    }

    public Node getContent() {
        return container;
    }

    public String getTitle() {
        return String.format("Nota %s", nota.getNummer());
    }

}
