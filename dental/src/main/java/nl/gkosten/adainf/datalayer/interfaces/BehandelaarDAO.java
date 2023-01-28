package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.datalayer.interfaces.DatalayerException;
import nl.gkosten.adainf.models.Behandelaar;

public interface BehandelaarDAO {

    public Behandelaar getBehandelaar(int bsn) throws DatalayerException;

    public void saveBehandelaar(Behandelaar behandelaar);
}
