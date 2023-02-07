package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Nota;

import java.util.List;

public interface NotaDAO {

    public List<Nota> getAllNotas() throws DatalayerException;

    public Nota getNota(int nummer) throws DatalayerException;

    public void saveNota(Nota nota) throws DatalayerException;

    public void updateNota(Nota nota) throws DatalayerException;

    public void deleteNota(Nota nota) throws DatalayerException;

}
