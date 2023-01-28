package nl.gkosten.adainf.models;

public class Behandeling {
    private String code;
    private double prijs;
    private String omschrijving;

    public Behandeling(String code, double prijs, String omschrijving) {
        this.code = code;
        this.prijs = prijs;
        this.omschrijving = omschrijving;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Behandeling{");
        sb.append("code='").append(code).append('\'');
        sb.append(", prijs=").append(prijs);
        sb.append(", omschrijving='").append(omschrijving).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
