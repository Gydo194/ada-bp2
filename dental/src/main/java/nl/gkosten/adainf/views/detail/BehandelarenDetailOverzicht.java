package nl.gkosten.adainf.views.detail;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.scene.Node;
        import javafx.scene.control.*;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Text;
        import nl.gkosten.adainf.App;
        import nl.gkosten.adainf.controllers.ErrorDialogController;
        import nl.gkosten.adainf.datalayer.DAOProvider;
        import nl.gkosten.adainf.datalayer.DatalayerException;
        import nl.gkosten.adainf.models.Behandelaar;
        import nl.gkosten.adainf.models.Geslacht;
        import nl.gkosten.adainf.views.Main;
        import nl.gkosten.adainf.views.overzicht.BehandelarenOverzicht;

        import java.time.Instant;
        import java.time.LocalDate;
        import java.time.ZoneId;
        import java.util.Date;

public class BehandelarenDetailOverzicht {
    private final Behandelaar behandelaar;
    private final VBox container;

    public BehandelarenDetailOverzicht(Behandelaar behandelaar) {
        this.behandelaar = behandelaar;

        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);
        container.setSpacing(App.VERTICAL_SPACING);
        container.getStyleClass().add("detail-content-area");

        Text title = new Text(getTitle());
        title.getStyleClass().add("header-text");
        container.getChildren().add(title);

        Label bsnLabel = new Label("BSN-nummer:");
        Label achternaamLabel = new Label("Achternaam:");
        Label voorlettersLabel = new Label("Voorletters:");
        Label geboortedatumLabel = new Label("Geboortedatum:");
        Label emailLabel = new Label("E-mail:");
        Label geslachtLabel = new Label("Geslacht:");
        Label agbcodeLabel = new Label("AGB-code:");

        Label bsnField = new Label(String.format("%d", behandelaar.getBsn())); //code kan niet worden aangepast
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







        Button updateButton = new Button("Opslaan");
        Button deleteButton = new Button("Verwijderen");

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

        formGrid.add(agbcodeLabel,          0, 6);
        formGrid.add(agbcodeField,          1, 6);

        formGrid.add(updateButton,          0, 7);
        formGrid.add(deleteButton,          1, 7);

        container.getChildren().add(formGrid);



        //populate inputs
        bsnField.setText(String.format("%d", behandelaar.getBsn()));
        achternaamField.setText(behandelaar.getAchternaam());
        voorlettersField.setText(behandelaar.getVoorletters());
        emailField.setText(behandelaar.getEmail());
        agbcodeField.setText(String.format("%d", behandelaar.getAgbcode()));
        geslachtBox.getSelectionModel().select(behandelaar.getGeslacht().toString());
        geboortedatumPicker.setValue( //aaaaaaaaaaaaaaaaaaaaaaaa
                Instant.ofEpochMilli(
                        behandelaar.getGeboortedatum()
                                .getTime()).atZone(ZoneId.systemDefault())
                                    .toLocalDate()
        );

        updateButton.setOnAction(actionEvent -> {

            int agbcode;
            String achternaam, voorletters, email;
            Geslacht geslacht;
            Date geboortedatum;

            try {
                agbcode = Integer.parseInt(agbcodeField.getText());
            } catch (NumberFormatException e) {
                ErrorDialogController.showDialog("Ongeldige Invoer", "Voer een geldige AGB-code in!");
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



            Behandelaar updated = new Behandelaar(
                    behandelaar.getBsn(),
                    achternaam,
                    voorletters,
                    geboortedatum,
                    email,
                    geslacht,
                    agbcode
            );

            try {
                DAOProvider.getBehandelaarDAO().updateBehandelaar(updated);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets misgegaan bij het wijzigen.");
                e.printStackTrace();

                return;
            }

            ErrorDialogController.showDialog("Item gewijzigd", "De behandelaar is gewijzigd.");
            BehandelarenOverzicht.updateData();
        });

        deleteButton.setOnAction(actionEvent -> {
            try {
                DAOProvider.getBehandelaarDAO().deleteBehandelaar(behandelaar);
            } catch (DatalayerException e) {
                ErrorDialogController.showError("Database fout", "Er is iets misgegaan bij het verwijderen.");
                e.printStackTrace();

                return;
            }

            ErrorDialogController.showDialog("Item verwijderd", "De behandelaar is verwijderd.");
            BehandelarenOverzicht.updateData();
            Main.closeTab(getTitle());
            Main.activateTab("Behandelaren");
        });

    }

    public Node getContent() {
        return container;
    }

    public String getTitle() {
        return String.format("Behandelaar %s, %s", behandelaar.getAchternaam(), behandelaar.getVoorletters());
    }

}
