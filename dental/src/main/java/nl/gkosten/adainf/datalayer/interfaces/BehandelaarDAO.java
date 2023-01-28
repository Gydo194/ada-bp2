package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandelaar;

import java.util.List;

public interface BehandelaarDAO {

    public List<Behandelaar> getAllBehandelaars() throws DatalayerException;

    public Behandelaar getBehandelaar(int bsn) throws DatalayerException;

    public void saveBehandelaar(Behandelaar behandelaar) throws DatalayerException;
}
