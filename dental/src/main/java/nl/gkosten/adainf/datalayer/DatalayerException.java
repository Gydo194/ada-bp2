package nl.gkosten.adainf.datalayer;

public class DatalayerException extends Exception {
    public DatalayerException() {
    }

    public DatalayerException(String message) {
        super(message);
    }

    public DatalayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatalayerException(Throwable cause) {
        super(cause);
    }

    public DatalayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
