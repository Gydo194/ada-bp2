package nl.gkosten.adainf.models;

public enum Geslacht {
    MAN,
    VROUW;

    @Override
    public String toString() {
        return switch (this) {
            case MAN -> "Man";
            case VROUW -> "Vrouw";
        };
    }

    public String toShortRepresentation() {
        return switch (this) {
            case MAN -> "M";
            case VROUW -> "V";
        };
    }

    public static Geslacht from(String s) {
        return switch(s) {
            case "M", "Man", "MAN" -> Geslacht.MAN;
            default -> Geslacht.VROUW;
        };
    }
}
