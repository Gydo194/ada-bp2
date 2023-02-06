package nl.gkosten.adainf.views.detail;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.gkosten.adainf.App;
import nl.gkosten.adainf.models.Behandeling;

public class BehandelingenDetailOverzicht {
    private Behandeling behandeling;
    private VBox container;

    public BehandelingenDetailOverzicht(Behandeling behandeling) {
        this.behandeling = behandeling;

        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);

        Text title = new Text(String.format("Behandeling '%s'", behandeling.getCode()));
        container.getChildren().add(title);

        Label codeLabel = new Label("Behandelingscode:");
        Label prijsLabel = new Label("Prijs:");
        Label omschrijvingLabel = new Label("Omschrijving:");

        TextField codeField = new TextField();
        TextField prijsField = new TextField();
        TextField omschrijvingField = new TextField();

        Button updateButton = new Button("Opslaan");
        Button deleteButton = new Button("Verwijderen");

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

        container.getChildren().add(formGrid);


    }

    public Node getContent() {
        return container;
    }

    public String getTitle() {
        return String.format("Behandeling %s", behandeling.getCode());
    }

}
