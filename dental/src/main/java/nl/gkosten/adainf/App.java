package nl.gkosten.adainf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.gkosten.adainf.views.Main;


/**
 * JavaFX App
 */
public class App extends Application {

    public static final int     PREFERRED_DIMENSIONS_X  = 1200;
    public static final int     PREFERRED_DIMENSIONS_Y  = 800;
    public static final double  VERTICAL_SPACING        = 10.0D;

    private static Stage stageHandle;

    @Override
    public void start(Stage stage) {
            stageHandle = stage;

            stageHandle.setScene(new Main().getHomeScene());
            stageHandle.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showScene(Scene scene) {
        stageHandle.setScene(scene);
    }

}