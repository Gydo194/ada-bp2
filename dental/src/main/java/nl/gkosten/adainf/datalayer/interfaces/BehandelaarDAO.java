package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandelaar;

public interface BehandelaarDAO {

    public Behandelaar getBehandelaar(int bsn) throws DatalayerException;

    public void saveBehandelaar(Behandelaar behandelaar) throws DatalayerException;
}
