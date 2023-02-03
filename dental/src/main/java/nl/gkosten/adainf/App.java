package nl.gkosten.adainf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.*;
import nl.gkosten.adainf.views.Home;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    public static final int PREFERRED_DIMENSIONS_X = 600;
    public static final int PREFERRED_DIMENSIONS_Y = 600;

    private static Stage stageHandle;

    @Override
    public void start(Stage stage) {
            stageHandle = stage;

            stageHandle.setScene(new Home().getHomeScene());
            stageHandle.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showScene(Scene scene) {
        stageHandle.setScene(scene);
    }

}