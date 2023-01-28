package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.models.Patient;
import nl.gkosten.adainf.datalayer.interfaces.DatalayerException;

public interface PatientDAO {

    public Patient getPatient(int bsn) throws DatalayerException;

    public void savePatient(Patient patient) throws DatalayerException;
}
