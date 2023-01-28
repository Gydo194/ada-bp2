package nl.gkosten.adainf.models;

import java.util.Date;

public class Behandelaar extends Persoon {

    private int agbcode;

    public Behandelaar(int bsn, String achternaam, String voorletters, Date geboortedatum, String email, Geslacht geslacht, int agbcode) {
        super(bsn, achternaam, voorletters, geboortedatum, email, geslacht);
    }

    public int getAgbcode() {
        return agbcode;
    }

    public void setAgbcode(int agbcode) {
        this.agbcode = agbcode;
    }
}