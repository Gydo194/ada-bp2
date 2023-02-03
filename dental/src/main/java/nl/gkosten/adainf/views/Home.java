package nl.gkosten.adainf.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import nl.gkosten.adainf.App;

public class Home {
    private final Scene homeScene;
    private final TilePane buttonPane;

    private static final double buttonSize = (double) App.PREFERRED_DIMENSIONS_X / 2.0D;

    public Home() {
        buttonPane = new TilePane();
        buttonPane.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        buttonPane.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);

        Button patientenOverzichtButton = new Button("Patienten");
        patientenOverzichtButton.setPrefWidth(buttonSize);
        patientenOverzichtButton.setPrefHeight(buttonSize);

        Button behandelarenOverzichtButton = new Button("Behandelaren");
        behandelarenOverzichtButton.setPrefWidth(buttonSize);
        behandelarenOverzichtButton.setPrefHeight(buttonSize);

        Button behandelingenOverzichtButton = new Button("Behandeling");
        behandelingenOverzichtButton.setPrefWidth(buttonSize);
        behandelingenOverzichtButton.setPrefHeight(buttonSize);
        behandelingenOverzichtButton.setOnAction(actionEvent -> {
            App.showScene(new BehandelingenOverzicht().getBehandelingenOverzichtScene());
        });

        Button notaOverzichtButton = new Button("Nota's");
        notaOverzichtButton.setPrefWidth(buttonSize);
        notaOverzichtButton.setPrefHeight(buttonSize);


        buttonPane.setMinSize(buttonSize, buttonSize);
        buttonPane.getChildren().addAll(
                patientenOverzichtButton,
                behandelarenOverzichtButton,
                behandelingenOverzichtButton,
                notaOverzichtButton
        );








        homeScene = new Scene(buttonPane);
    }

    public Scene getHomeScene() {
        return homeScene;
    }

}
