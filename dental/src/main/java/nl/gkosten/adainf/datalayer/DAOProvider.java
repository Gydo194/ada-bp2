package nl.gkosten.adainf.datalayer;

import nl.gkosten.adainf.datalayer.implementations.MysqlBehandelaarDAO;
import nl.gkosten.adainf.datalayer.implementations.MysqlBehandelingDAO;
import nl.gkosten.adainf.datalayer.interfaces.BehandelaarDAO;
import nl.gkosten.adainf.datalayer.interfaces.BehandelingDAO;

public class DAOProvider {
    private static final BehandelingDAO behandelingDAO;
    private static final BehandelaarDAO behandelaarDAO;

    static {
        behandelingDAO = new MysqlBehandelingDAO();
        behandelaarDAO = new MysqlBehandelaarDAO();
    }

    public static BehandelingDAO getBehandelingDAO() {
        return behandelingDAO;
    }

    public static BehandelaarDAO getBehandelaarDAO() {
        return behandelaarDAO;
    }

}
