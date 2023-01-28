package nl.gkosten.adainf.datalayer.implementations;

import nl.gkosten.adainf.datalayer.interfaces.BehandelaarDAO;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandelaar;

import java.util.ArrayList;
import java.util.List;

public class MysqlBehandelaarDAO implements BehandelaarDAO {

    public List<Behandelaar> getAllBehandelaars() throws DatalayerException {
        return new ArrayList<>();
    }

    @Override
    public Behandelaar getBehandelaar(int bsn) throws DatalayerException {
        return null;
    }

    @Override
    public void saveBehandelaar(Behandelaar behandelaar) {

    }
}
