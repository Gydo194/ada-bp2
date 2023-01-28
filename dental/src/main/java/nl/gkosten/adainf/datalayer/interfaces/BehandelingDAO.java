package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.models.Behandeling;
import nl.gkosten.adainf.datalayer.interfaces.DatalayerException;

public interface BehandelingDAO {
    public Behandeling getBehandeling(String code) throws DatalayerException;
}
