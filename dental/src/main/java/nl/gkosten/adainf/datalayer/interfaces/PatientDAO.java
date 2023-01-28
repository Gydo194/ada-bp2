package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Patient;

public interface PatientDAO {

    public Patient getPatient(int bsn) throws DatalayerException;

    public void savePatient(Patient patient) throws DatalayerException;
}
