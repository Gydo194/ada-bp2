package nl.gkosten.adainf.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.gkosten.adainf.App;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandeling;

public class BehandelingenOverzicht {
    private Scene behandelingenOverzichtScene;
    private VBox container;

    public BehandelingenOverzicht() {
        container = new VBox();
        container.setPrefWidth(App.PREFERRED_DIMENSIONS_X);
        container.setPrefHeight(App.PREFERRED_DIMENSIONS_Y);


        Text title = new Text("Behandelingen");
        container.getChildren().add(title);

        TableView behandelingenTable = new TableView();

        TableColumn codeColumn = new TableColumn("Code");
        codeColumn.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );

        TableColumn omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(
                new PropertyValueFactory<>("omschrijving")
        );

        TableColumn prijsColumn = new TableColumn("Prijs");
        prijsColumn.setCellValueFactory(
                new PropertyValueFactory<>("prijs")
        );

        behandelingenTable.getColumns().addAll(
                codeColumn,
                omschrijvingColumn,
                prijsColumn
        );

        ObservableList<Behandeling> behandelingen = FXCollections.observableArrayList();

        try {
            behandelingen = FXCollections.observableArrayList(
                    DAOProvider.getBehandelingDAO().getAllBehandelingen()
            );
        } catch (DatalayerException e) {
            throw new RuntimeException(e);
        }

        behandelingenTable.setItems(behandelingen);

        container.getChildren().add(behandelingenTable);

        behandelingenOverzichtScene = new Scene(container);
    }

    public Scene getBehandelingenOverzichtScene() {
        return behandelingenOverzichtScene;
    }
}
