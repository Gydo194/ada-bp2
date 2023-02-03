package nl.gkosten.adainf.views;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import nl.gkosten.adainf.App;

public class BehandelingenOverzicht {
    private Scene behandelingenOverzichtScene;
    private VBox container;

    public BehandelingenOverzicht() {
        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);

        behandelingenOverzichtScene = new Scene(container);
    }

    public Scene getBehandelingenOverzichtScene() {
        return behandelingenOverzichtScene;
    }
}
