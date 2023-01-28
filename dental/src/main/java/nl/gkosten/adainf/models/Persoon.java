package nl.gkosten.adainf.models;

import java.util.Date;

public record Persoon(int bsn, String achternaam, String voorletters, Date geboortedatum, Geslacht geslacht, String email) {
}
