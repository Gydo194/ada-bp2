package nl.gkosten.adainf.datalayer.interfaces;

import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.models.Patient;

import java.util.List;

public interface PatientDAO {

    public List<Patient> getAllPatienten() throws DatalayerException;

    public Patient getPatient(int bsn) throws DatalayerException;

    public void savePatient(Patient patient) throws DatalayerException;

    public void updatePatient(Patient patient) throws DatalayerException;

    public void deletePatient(Patient patient) throws DatalayerException;

}
