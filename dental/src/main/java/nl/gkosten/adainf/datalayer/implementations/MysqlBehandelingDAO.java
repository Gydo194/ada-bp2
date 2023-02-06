package nl.gkosten.adainf.datalayer.implementations;

import nl.gkosten.adainf.database.StatementFactory;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.datalayer.interfaces.BehandelingDAO;
import nl.gkosten.adainf.models.Behandeling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlBehandelingDAO implements BehandelingDAO {

    private List<Behandeling> instantiateFromQueryResults(ResultSet resultSet) throws SQLException {

        List<Behandeling> behandelingen = new ArrayList<>();

        while(resultSet.next()) {

            Behandeling current = new Behandeling(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3)
            );

            behandelingen.add(current);

        }

        return behandelingen;
    }

    @Override
    public List<Behandeling> getAllBehandelingen() throws DatalayerException {
        try {
            String query = "SELECT behandelingscode, prijs, omschrijving FROM behandeling;";
            Statement statement = StatementFactory.getInstance().createStatement();
            ResultSet result = statement.executeQuery(query);

            return instantiateFromQueryResults(result);

        } catch (SQLException sqlException) {
            System.out.printf("MysqlBehandelingDao::getAllBehandelingen(): SQLexception: %s\n", sqlException.getMessage());
            sqlException.printStackTrace();
        }

        throw new DatalayerException();
    }

    @Override
    public Behandeling getBehandeling(String code) throws DatalayerException {
        try {
            String query = String.format(
                    "SELECT behandelingscode, prijs, omschrijving FROM behandeling WHERE behandelingscode = '%s' LIMIT 1;",
                    code
            );
            Statement statement = StatementFactory.getInstance().createStatement();
            ResultSet result = statement.executeQuery(query);

            List<Behandeling> behandelingen = instantiateFromQueryResults(result);

            assert 1 == behandelingen.size() : "MysqlBehandelingDAO::getBehandeling(): assertion failed: 1 == behandelingen.size()";

            return behandelingen.get(0);

        } catch (SQLException sqlException) {
            System.out.printf("MysqlBehandelingDao::getBehandeling('%s'): SQLexception: %s\n", code, sqlException.getMessage());
            sqlException.printStackTrace();
        }

        throw new DatalayerException();
    }

    @Override
    public void saveBehandeling(Behandeling behandeling) throws DatalayerException {
        try {
            String query = String.format(
                    "INSERT INTO behandeling VALUES('%s', %f, '%s');",
                    behandeling.getCode(),
                    behandeling.getPrijs(),
                    behandeling.getOmschrijving()
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            System.out.printf("MysqlBehandelingDao::saveBehandeling(): SQLexception: %s\n", sqlException.getMessage());
            sqlException.printStackTrace();

            throw new DatalayerException();
        }
    }

    @Override
    public void updateBehandeling(Behandeling behandeling) throws DatalayerException {
        try {
            String query = String.format(
                    "UPDATE behandeling SET prijs = %f, omschrijving = '%s' WHERE behandelingscode = '%s';",
                    behandeling.getPrijs(),
                    behandeling.getOmschrijving(),
                    behandeling.getCode()
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            System.out.printf("MysqlBehandelingDao::updateBehandeling(): SQLexception: %s\n", sqlException.getMessage());
            sqlException.printStackTrace();

            throw new DatalayerException();
        }
    }

    @Override
    public void deleteBehandeling(Behandeling behandeling) throws DatalayerException {
        try {
            String query = String.format(
                    "DELETE FROM behandeling WHERE behandelingscode = '%s';",
                    behandeling.getCode()
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            System.out.printf("MysqlBehandelingDao::deleteBehandeling(): SQLexception: %s\n", sqlException.getMessage());
            sqlException.printStackTrace();

            throw new DatalayerException();
        }
    }
}
