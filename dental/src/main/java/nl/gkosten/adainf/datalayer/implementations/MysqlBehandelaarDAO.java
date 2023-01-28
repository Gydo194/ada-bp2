package nl.gkosten.adainf.datalayer.implementations;

import nl.gkosten.adainf.datalayer.interfaces.BehandelaarDAO;
import nl.gkosten.adainf.datalayer.interfaces.DatalayerException;
import nl.gkosten.adainf.models.Behandelaar;

public class MysqlBehandelaarDAO implements BehandelaarDAO {
    @Override
    public Behandelaar getBehandelaar(int bsn) throws DatalayerException {
        return null;
    }

    @Override
    public void saveBehandelaar(Behandelaar behandelaar) {

    }
}
