package nl.gkosten.adainf.models;

import java.util.Date;

public class Persoon {
    private int bsn;
    private String achternaam;
    private String voorletters;
    private Date geboortedatum;
    private String email;
    private Geslacht geslacht;

    public Persoon(int bsn, String achternaam, String voorletters, Date geboortedatum, String email, Geslacht geslacht) {
        this.bsn = bsn;
        this.achternaam = achternaam;
        this.voorletters = voorletters;
        this.geboortedatum = geboortedatum;
        this.email = email;
        this.geslacht = geslacht;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }
}
