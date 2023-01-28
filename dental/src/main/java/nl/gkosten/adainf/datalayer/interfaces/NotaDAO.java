package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.datalayer.interfaces.DatalayerException;
import nl.gkosten.adainf.models.Nota;

public interface NotaDAO {

    public Nota getNota(int nummer) throws DatalayerException;

    public void saveNota(Nota nota) throws DatalayerException;
}
