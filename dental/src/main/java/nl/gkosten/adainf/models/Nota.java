package nl.gkosten.adainf.models;

import java.util.Date;
import java.util.Random;

public class Nota {

    private int nummer;
    private Patient patient;
    private Behandeling behandeling;
    private Date startdatum;
    private Date einddatum;

    public Nota(Patient patient, Behandeling behandeling, Date startdatum, Date einddatum) {
        this.nummer = new Random().nextInt(); //create the id here instead of in the database to prevent invalid entities
        this.patient = patient;
        this.behandeling = behandeling;
        this.startdatum = startdatum;
        this.einddatum = einddatum;
    }

    public Nota(int nummer, Patient patient, Behandeling behandeling, Date startdatum, Date einddatum) {
        this.nummer = nummer;
        this.patient = patient;
        this.behandeling = behandeling;
        this.startdatum = startdatum;
        this.einddatum = einddatum;
    }

    public int getNummer() {
        return nummer;
    }

    public Patient getPatient() {
        return patient;
    }

    public Behandeling getBehandeling() {
        return behandeling;
    }

    public Date getStartdatum() {
        return startdatum;
    }

    public Date getEinddatum() {
        return einddatum;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Nota{");
        sb.append("nummer=").append(nummer);
        sb.append(", patient=").append(patient);
        sb.append(", behandeling=").append(behandeling);
        sb.append(", startdatum=").append(startdatum);
        sb.append(", einddatum=").append(einddatum);
        sb.append('}');
        return sb.toString();
    }

}
