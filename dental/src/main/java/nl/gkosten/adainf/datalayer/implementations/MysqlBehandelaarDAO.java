package nl.gkosten.adainf.datalayer.implementations;

import nl.gkosten.adainf.database.StatementFactory;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.datalayer.interfaces.BehandelaarDAO;
import nl.gkosten.adainf.models.Behandelaar;
import nl.gkosten.adainf.models.Geslacht;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlBehandelaarDAO implements BehandelaarDAO {

    private static Geslacht parseGeslacht(String s) throws DatalayerException {
        return switch (s) {
            case "M" -> Geslacht.MAN;
            case "V" -> Geslacht.VROUW;
            default -> throw new DatalayerException(
                    String.format(
                            "Invalid value for geslacht: '%s'",
                            s
                    )
            );
        };
    }

    private List<Behandelaar> instantiateFromQueryResults(ResultSet resultSet) throws SQLException, DatalayerException {

        List<Behandelaar> behandelaars = new ArrayList<>();

        while(resultSet.next()) {

            Behandelaar current = new Behandelaar(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getString(6),
                    parseGeslacht(resultSet.getString(5)),
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
        return null;
    }

    @Override
    public void saveBehandelaar(Behandelaar behandelaar) {

    }
}
