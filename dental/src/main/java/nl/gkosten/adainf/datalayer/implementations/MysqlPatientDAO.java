package nl.gkosten.adainf.datalayer.implementations;

import nl.gkosten.adainf.database.StatementFactory;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.datalayer.interfaces.PatientDAO;
import nl.gkosten.adainf.models.Geslacht;
import nl.gkosten.adainf.models.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MysqlPatientDAO implements PatientDAO {


    private List<Patient> instantiateFromQueryResults(ResultSet resultSet) throws SQLException, DatalayerException {

        List<Patient> patienten = new ArrayList<>();

        while(resultSet.next()) {

            Patient current = new Patient(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getString(6),
                    Geslacht.from(resultSet.getString(5)),
                    resultSet.getInt(7)
            );

            patienten.add(current);

        }

        return patienten;
    }

    public List<Patient> getAllPatienten() throws DatalayerException {
        try {
            String query = "SELECT bsn, achternaam, voorletters, geboortedatum, geslacht, email, relatienummer FROM persoon WHERE type = 'PATIENT';";
            Statement statement = StatementFactory.getInstance().createStatement();
            ResultSet result = statement.executeQuery(query);

            return instantiateFromQueryResults(result);

        } catch (SQLException sqlException) {
            throw new DatalayerException("getAllPatienten(): SQLexception:", sqlException);
        }
    }

    @Override
    public Patient getPatient(int bsn) throws DatalayerException {
        try {
            String query = String.format(
                    "SELECT bsn, achternaam, voorletters, geboortedatum, geslacht, email, relatienummer FROM persoon WHERE type = 'PATIENT' AND bsn = %d LIMIT 1;",
                    bsn
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            ResultSet result = statement.executeQuery(query);

            List<Patient> results = instantiateFromQueryResults(result);
            if(results.isEmpty()) {
                throw new DatalayerException("getAllPatients(): No Results");
            }

            return results.get(0);

        } catch (SQLException sqlException) {
            throw new DatalayerException("MysqlPatientDAO::getPatient(): SQLexception:", sqlException);
        }

    }

    @Override
    public Patient getPatientByRelNr(int relatienummer) throws DatalayerException {
        try {
            String query = String.format(
                    "SELECT bsn, achternaam, voorletters, geboortedatum, geslacht, email, relatienummer FROM persoon WHERE type = 'PATIENT' AND relatienummer = %d LIMIT 1;",
                    relatienummer
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            ResultSet result = statement.executeQuery(query);

            List<Patient> results = instantiateFromQueryResults(result);
            if(results.isEmpty()) {
                throw new DatalayerException("MysqlPatientDAO::getPatientByRelNr(): No Results");
            }

            return results.get(0);

        } catch (SQLException sqlException) {
            throw new DatalayerException("getAllPatients(): SQLexception:", sqlException);
        }
    }

    @Override
    public void savePatient(Patient patient) throws DatalayerException {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String query = String.format(
                    "INSERT INTO persoon (bsn, achternaam, voorletters, geboortedatum, email, geslacht, relatienummer, type) VALUES(%d, '%s', '%s', '%s', '%s', '%s', %d, 'PATIENT');",
                    patient.getBsn(),
                    patient.getAchternaam(),
                    patient.getVoorletters(),
                    formatter.format(patient.getGeboortedatum()),
                    patient.getEmail(),
                    patient.getGeslacht().toShortRepresentation(),
                    patient.getRelatienummer()
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            throw new DatalayerException("MysqlPatientDao::savePatient(): SQLexception: %s\n", sqlException);
        }
    }

    @Override
    public void updatePatient(Patient patient) throws DatalayerException {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String query = String.format(
                    "UPDATE persoon SET achternaam = '%s', voorletters = '%s', geboortedatum = '%s', email = '%s', geslacht = '%s', relatienummer = %d WHERE type = 'PATIENT' AND bsn = %d;",
                    patient.getAchternaam(),
                    patient.getVoorletters(),
                    formatter.format(patient.getGeboortedatum()),
                    patient.getEmail(),
                    patient.getGeslacht().toShortRepresentation(),
                    patient.getRelatienummer(),
                    patient.getBsn()
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            throw new DatalayerException("MysqlPatientDao::savePatient(): SQLexception: %s\n", sqlException);
        }

    }

    @Override
    public void deletePatient(Patient patient) throws DatalayerException {
        try {
            String query = String.format(
                    "DELETE FROM persoon WHERE bsn = %d AND type = 'PATIENT';",
                    patient.getBsn()
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new DatalayerException("MysqlPatientDao::deletePatient(): SQLexception", sqlException);
        }

    }
}
