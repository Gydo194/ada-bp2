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
        this.nummer = new Random().nextInt();
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
}
