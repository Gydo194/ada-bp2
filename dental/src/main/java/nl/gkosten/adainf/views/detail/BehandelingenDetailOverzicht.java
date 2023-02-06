package nl.gkosten.adainf.views.detail;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
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


    }

    public Node getContent() {
        return container;
    }

}
