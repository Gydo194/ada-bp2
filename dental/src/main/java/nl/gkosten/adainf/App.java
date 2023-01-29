package nl.gkosten.adainf;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

            System.out.println("\n\n\n\n\n");

            List<Behandelaar> behandelaars = DAOProvider.getBehandelaarDAO().getAllBehandelaars();
            for(Behandelaar i : behandelaars) {
                System.out.println("BEHANDELAAR: " + i);
            }

            System.out.println("\n\n\n\n\n");

            System.out.println(DAOProvider.getBehandelaarDAO().getBehandelaar(237944161));


            /*
            DateFormat g= new SimpleDateFormat("yyyy-MM-dd");
            Date h = g.parse("1970-11-30");
            Behandelaar r = new Behandelaar(237943978, "Overbeeke", "M.", h, "moverbeeke@zeelandnet.nl", Geslacht.VROUW, 12000006);
            DAOProvider.getBehandelaarDAO().saveBehandelaar(r);
            */

            System.out.println("\n\n\n\n\n");

            List<Patient> patienten = DAOProvider.getPatientDAO().getAllPatienten();
            for(Patient p : patienten) {
                System.out.println("PATIENT: " + p);
            }

            Patient gydo = DAOProvider.getPatientDAO().getPatient(237943979);
            System.out.println(gydo);


            /*
            DateFormat g= new SimpleDateFormat("yyyy-MM-dd");
            Date h = g.parse("1970-11-30");
            Patient m = new Patient(237943977, "Kosten", "M.", h, "marco.kosten@mail.nl", Geslacht.MAN, 1608022);
            DAOProvider.getPatientDAO().savePatient(m);
            */

            System.out.println("\n\n\n\n\n");
            List<Nota> notas = DAOProvider.getNotaDAO().getAllNotas();
            for(Nota n : notas) {
                System.out.println("NOTA: " + n);
            }


        } catch (DatalayerException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch();
    }

}