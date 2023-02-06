package nl.gkosten.adainf.models;

import java.util.Date;

public class Patient extends Persoon {

    private int relatienummer;

    public Patient(int bsn, String achternaam, String voorletters, Date geboortedatum, String email, Geslacht geslacht, int relatienummer) {
        super(bsn, achternaam, voorletters, geboortedatum, email, geslacht);

        this.relatienummer = relatienummer;
    }

    public int getRelatienummer() {
        return relatienummer;
    }

    public void setRelatienummer(int relatienummer) {
        this.relatienummer = relatienummer;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getAchternaam(), getVoorletters());
    }
}
