package nl.gkosten.adainf.views;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import nl.gkosten.adainf.App;
import nl.gkosten.adainf.views.overzicht.BehandelarenOverzicht;
import nl.gkosten.adainf.views.overzicht.BehandelingenOverzicht;
import nl.gkosten.adainf.views.overzicht.NotaOverzicht;
import nl.gkosten.adainf.views.overzicht.PatientenOverzicht;

import java.util.ArrayList;
import java.util.List;

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

        Tab patientenTab = new Tab("Patienten");
        patientenTab.setContent(PatientenOverzicht.getContent());
        patientenTab.setClosable(false);
        container.getTabs().add(patientenTab);

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

    public static void closeTab(String title) {
        List<Tab> toRemove = new ArrayList<>();

        for(Tab tab : container.getTabs()) {
            if(tab.getText().equals(title)) {
                toRemove.add(tab);
            }
        }

        //run outside of foreach because of concurrentmodificationexception
        for(Tab tabToRemove : toRemove) {
            container.getTabs().remove(tabToRemove);
        }
    }

    public static void activateTab(String title) {
        for(Tab tab : container.getTabs()) {
            if(tab.getText().equals(title)) {
                container.getSelectionModel().select(tab);
            }
        }
    }

    public Scene getHomeScene() {
        return homeScene;
    }

}
