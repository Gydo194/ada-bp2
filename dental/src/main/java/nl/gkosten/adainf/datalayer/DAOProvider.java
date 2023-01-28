package nl.gkosten.adainf.datalayer;

import nl.gkosten.adainf.datalayer.implementations.MysqlBehandelingDAO;
import nl.gkosten.adainf.datalayer.interfaces.BehandelingDAO;

public class DAOProvider {
    private static final BehandelingDAO behandelingDAO;

    static {
        behandelingDAO = new MysqlBehandelingDAO();
    }

    public static BehandelingDAO getBehandelingDAO() {
        return behandelingDAO;
    }
}
