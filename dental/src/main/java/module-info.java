module nl.gkosten.adainf {
    requires javafx.controls;
    requires java.sql;
    exports nl.gkosten.adainf;

    opens nl.gkosten.adainf.models to javafx.base;
}
