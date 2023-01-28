package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Behandeling;

import java.util.List;

public interface BehandelingDAO {

    public List<Behandeling> getAllBehandelingen() throws DatalayerException;

    public Behandeling getBehandeling(String code) throws DatalayerException;

    public void saveBehandeling(Behandeling behandeling) throws DatalayerException;
}
