package nl.gkosten.adainf.datalayer.implementations;

import nl.gkosten.adainf.database.StatementFactory;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.datalayer.interfaces.BehandelaarDAO;
import nl.gkosten.adainf.models.Behandelaar;
import nl.gkosten.adainf.models.Geslacht;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MysqlBehandelaarDAO implements BehandelaarDAO {

    private List<Behandelaar> instantiateFromQueryResults(ResultSet resultSet) throws SQLException, DatalayerException {

        List<Behandelaar> behandelaars = new ArrayList<>();

        while(resultSet.next()) {

            Behandelaar current = new Behandelaar(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getString(6),
                    Geslacht.from(resultSet.getString(5)),
                    resultSet.getInt(7)
            );

            behandelaars.add(current);

        }

        return behandelaars;
    }

    public List<Behandelaar> getAllBehandelaars() throws DatalayerException {
        try {
            String query = "SELECT bsn, achternaam, voorletters, geboortedatum, geslacht, email, agbcode FROM persoon WHERE type = 'BEHANDELAAR';";
            Statement statement = StatementFactory.getInstance().createStatement();
            ResultSet result = statement.executeQuery(query);

            return instantiateFromQueryResults(result);

        } catch (SQLException sqlException) {
            System.out.printf("MysqlBehandelaarDao::getAllBehandelaars(): SQLexception: %s\n", sqlException.getMessage());
            sqlException.printStackTrace();

            throw new DatalayerException("getAllBehandelaars(): SQLexception:", sqlException);
        }

    }

    @Override
    public Behandelaar getBehandelaar(int bsn) throws DatalayerException {
        try {
            String query = String.format(
                    "SELECT bsn, achternaam, voorletters, geboortedatum, geslacht, email, agbcode FROM persoon WHERE type = 'BEHANDELAAR' AND bsn = %d LIMIT 1;",
                    bsn
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            ResultSet result = statement.executeQuery(query);

            return instantiateFromQueryResults(result).get(0);

        } catch (SQLException sqlException) {
            System.out.printf("MysqlBehandelaarDao::getAllBehandelaars(): SQLexception: %s\n", sqlException.getMessage());
            sqlException.printStackTrace();

            throw new DatalayerException("getAllBehandelaars(): SQLexception:", sqlException);
        }

    }

    @Override
    public void saveBehandelaar(Behandelaar behandelaar) throws DatalayerException {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String query = String.format(
                    "INSERT INTO persoon (bsn, achternaam, voorletters, geboortedatum, email, geslacht, agbcode, type) VALUES(%d, '%s', '%s', '%s', '%s', '%s', %d, 'BEHANDELAAR');",
                    behandelaar.getBsn(),
                    behandelaar.getAchternaam(),
                    behandelaar.getVoorletters(),
                    formatter.format(behandelaar.getGeboortedatum()),
                    behandelaar.getEmail(),
                    behandelaar.getGeslacht().toString(),
                    behandelaar.getAgbcode()
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            throw new DatalayerException("MysqlBehandelaarDao::saveBehandelaar(): SQLexception: %s\n", sqlException);
        }
    }
}
