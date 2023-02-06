package nl.gkosten.adainf.views;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import nl.gkosten.adainf.App;
import nl.gkosten.adainf.views.overzicht.BehandelarenOverzicht;
import nl.gkosten.adainf.views.overzicht.BehandelingenOverzicht;
import nl.gkosten.adainf.views.overzicht.NotaOverzicht;

public class Main {
    private final Scene homeScene;
    private static TabPane container;

    public Main() {
        container = new TabPane();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);

        Tab behandelingenTab = new Tab("Behandelingen");
        behandelingenTab.setContent(BehandelingenOverzicht.getContent());
        behandelingenTab.setClosable(false);
        container.getTabs().add(behandelingenTab);

        Tab behandelarenTab = new Tab("Behandelaren");
        behandelarenTab.setContent(BehandelarenOverzicht.getContent());
        behandelarenTab.setClosable(false);
        container.getTabs().add(behandelarenTab);

        Tab notaTab = new Tab("Nota's");
        notaTab.setContent(NotaOverzicht.getContent());
        notaTab.setClosable(false);
        container.getTabs().add(notaTab);



        homeScene = new Scene(container);
    }

    public static void addTab(Node content, String title) {
        Tab tab = new Tab(title);
        tab.setClosable(true);
        tab.setContent(content);

        container.getTabs().add(tab);
        container.getSelectionModel().select(tab);
    }

    public Scene getHomeScene() {
        return homeScene;
    }

}
