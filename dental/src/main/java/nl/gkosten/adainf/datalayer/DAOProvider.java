package nl.gkosten.adainf.datalayer;

import nl.gkosten.adainf.datalayer.implementations.MysqlBehandelaarDAO;
import nl.gkosten.adainf.datalayer.implementations.MysqlBehandelingDAO;
import nl.gkosten.adainf.datalayer.implementations.MysqlPatientDAO;
import nl.gkosten.adainf.datalayer.interfaces.BehandelaarDAO;
import nl.gkosten.adainf.datalayer.interfaces.BehandelingDAO;
import nl.gkosten.adainf.datalayer.interfaces.PatientDAO;

public class DAOProvider {
    private static final BehandelingDAO behandelingDAO;
    private static final BehandelaarDAO behandelaarDAO;
    private static final PatientDAO patientDAO;

    static {
        behandelingDAO = new MysqlBehandelingDAO();
        behandelaarDAO = new MysqlBehandelaarDAO();
        patientDAO     = new MysqlPatientDAO();
    }

    public static BehandelingDAO getBehandelingDAO() {
        return behandelingDAO;
    }

    public static BehandelaarDAO getBehandelaarDAO() {
        return behandelaarDAO;
    }

    public static PatientDAO getPatientDAO() {
        return patientDAO;
    }

}
