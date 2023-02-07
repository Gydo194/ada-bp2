package nl.gkosten.adainf.datalayer.implementations;

import nl.gkosten.adainf.database.StatementFactory;
import nl.gkosten.adainf.datalayer.DAOProvider;
import nl.gkosten.adainf.datalayer.DatalayerException;
import nl.gkosten.adainf.datalayer.interfaces.NotaDAO;
import nl.gkosten.adainf.models.Nota;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MysqlNotaDAO implements NotaDAO {

    private static List<Nota> instantiateFromResultSet(ResultSet result) throws SQLException, DatalayerException {
        List<Nota> notas = new ArrayList<>();

        while(result.next()) {

            Nota current = new Nota(
                result.getInt(1),
                    DAOProvider.getPatientDAO().getPatient(result.getInt(2)),
                    DAOProvider.getBehandelingDAO().getBehandeling(result.getString(3)),
                    result.getDate(4),
                    result.getDate(5)
            );

            notas.add(current);
        }

        return notas;
    }

    @Override
    public List<Nota> getAllNotas() throws DatalayerException {

        try {
            final String query = "SELECT notanummer, patient, behandeling, startdatum, einddatum FROM nota;";
            Statement statement = StatementFactory.getInstance().createStatement();

            ResultSet result = statement.executeQuery(query);

            return instantiateFromResultSet(result);

        } catch (SQLException e) {
            throw new DatalayerException(e);
        }

    }

    @Override
    public Nota getNota(int nummer) throws DatalayerException {

        try {

            final String query = String.format(
                    "SELECT notanummer, patient, behandeling, startdatum, einddatum FROM nota WHERE notanummer = %d;",
                    nummer
            );

            Statement statement = StatementFactory.getInstance().createStatement();

            ResultSet result = statement.executeQuery(query);

            return instantiateFromResultSet(result).get(0);

        } catch (SQLException e) {
            throw new DatalayerException(e);
        }
    }

    @Override
    public void saveNota(Nota nota) throws DatalayerException {

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String query = String.format(
                    "INSERT INTO nota VALUES(%d, %d, '%s', '%s', '%s');",
                    nota.getNummer(),
                    nota.getPatient().getBsn(),
                    nota.getBehandeling().getCode(),
                    formatter.format(nota.getStartdatum()),
                    formatter.format(nota.getEinddatum())
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            throw new DatalayerException(sqlException);
        }
    }

    @Override
    public void updateNota(Nota nota) throws DatalayerException {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String query = String.format(
                    "UPDATE nota SET patient = %d, behandeling = '%s', startdatum = '%s', einddatum = '%s' WHERE notanummer = %d;",
                    nota.getPatient().getBsn(),
                    nota.getBehandeling().getCode(),
                    formatter.format(nota.getStartdatum()),
                    formatter.format(nota.getEinddatum()),
                    nota.getNummer()
            );

            System.out.println(query);

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            throw new DatalayerException(sqlException);
        }

    }

    @Override
    public void deleteNota(Nota nota) throws DatalayerException {
        try {
            String query = String.format(
                    "DELETE FROM nota WHERE notanummer = %d;",
                    nota.getNummer()
            );

            Statement statement = StatementFactory.getInstance().createStatement();
            statement.execute(query);

        } catch (SQLException sqlException) {
            throw new DatalayerException(sqlException);
        }

    }
}
