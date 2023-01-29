package nl.gkosten.adainf.datalayer;

import nl.gkosten.adainf.datalayer.implementations.MysqlBehandelaarDAO;
import nl.gkosten.adainf.datalayer.implementations.MysqlBehandelingDAO;
import nl.gkosten.adainf.datalayer.implementations.MysqlNotaDAO;
import nl.gkosten.adainf.datalayer.implementations.MysqlPatientDAO;
import nl.gkosten.adainf.datalayer.interfaces.BehandelaarDAO;
import nl.gkosten.adainf.datalayer.interfaces.BehandelingDAO;
import nl.gkosten.adainf.datalayer.interfaces.NotaDAO;
import nl.gkosten.adainf.datalayer.interfaces.PatientDAO;

public class DAOProvider {
    private static final BehandelingDAO behandelingDAO;
    private static final BehandelaarDAO behandelaarDAO;
    private static final PatientDAO patientDAO;
    private static final NotaDAO notaDAO;

    static {
        behandelingDAO = new MysqlBehandelingDAO();
        behandelaarDAO = new MysqlBehandelaarDAO();
        patientDAO     = new MysqlPatientDAO();
        notaDAO        = new MysqlNotaDAO();
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

    public static NotaDAO getNotaDAO() {
        return notaDAO;
    }
}
