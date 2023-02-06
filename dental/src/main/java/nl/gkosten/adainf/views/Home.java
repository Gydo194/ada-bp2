package nl.gkosten.adainf.views;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import nl.gkosten.adainf.App;

public class Home {
    private final Scene homeScene;

    public Home() {
        TabPane container = new TabPane();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);

        Tab behandelingenTab = new Tab("Behandelingen");
        behandelingenTab.setContent(new BehandelingenOverzicht().getContent());
        behandelingenTab.setClosable(false);
        container.getTabs().add(behandelingenTab);

        Tab behandelarenTab = new Tab("Behandelaren");
        behandelarenTab.setContent(new BehandelarenOverzicht().getContent());
        behandelarenTab.setClosable(false);
        container.getTabs().add(behandelarenTab);

        Tab notaTab = new Tab("Nota's");
        notaTab.setContent(new NotaOverzicht().getContent());
        notaTab.setClosable(false);
        container.getTabs().add(notaTab);



        homeScene = new Scene(container);
    }

    public Scene getHomeScene() {
        return homeScene;
    }

}
