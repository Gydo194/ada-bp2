package nl.gkosten.adainf.views.detail;

        import javafx.scene.Node;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Text;
        import nl.gkosten.adainf.App;
        import nl.gkosten.adainf.controllers.ErrorDialogController;
        import nl.gkosten.adainf.datalayer.DAOProvider;
        import nl.gkosten.adainf.datalayer.DatalayerException;
        import nl.gkosten.adainf.models.Nota;
        import nl.gkosten.adainf.views.Main;
        import nl.gkosten.adainf.views.overzicht.NotaOverzicht;

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
        Label omschrijvingLabel = new Label("Behandeling:");
        Label prijsLabel = new Label("Prijs:");

        Label notanummerField = new Label(String.format("%d", nota.getNummer())); //notanummer kan niet worden aangepast
        TextField prijsField = new TextField();
        TextField omschrijvingField = new TextField();

        Button updateButton = new Button("Opslaan");
        Button deleteButton = new Button("Verwijderen");

        GridPane formGrid = new GridPane();
        formGrid.setPrefWidth(App.PREFERRED_DIMENSIONS_X);

        formGrid.add(notanummerLabel,             0, 0);
        formGrid.add(notanummerField,             1, 0);
        formGrid.add(prijsLabel,            0, 1);
        formGrid.add(prijsField,            1, 1);
        formGrid.add(omschrijvingLabel,     0, 2);
        formGrid.add(omschrijvingField,     1, 2);
        formGrid.add(updateButton,          0, 3);
        formGrid.add(deleteButton,          1, 3);

        container.getChildren().add(formGrid);





        notanummerField.setText(String.format("%d", nota.getNummer()));
        prijsField.setText(String.format("%.02f", nota.getPrijs()));
        omschrijvingField.setText(nota.getBehandeling().getCode());

        updateButton.setOnAction(actionEvent -> {

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
