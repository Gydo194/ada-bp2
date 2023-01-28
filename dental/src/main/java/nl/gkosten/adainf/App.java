package nl.gkosten.adainf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandeling;

import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();

        try {
            Behandeling b = DAOProvider.getBehandelingDAO().getBehandeling("H11");
            System.out.println("BEHANDELING: " + b);


            //Behandeling x =new Behandeling("R08", 79.79D, "Eénvlaks composiet inlay");
            //DAOProvider.getBehandelingDAO().saveBehandeling(x);

            List<Behandeling> behandelingen = DAOProvider.getBehandelingDAO().getAllBehandelingen();
            for (Behandeling i: behandelingen) {
                System.out.println("BEHANDELING: " + i);
            }

        } catch (DatalayerException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch();
    }

}