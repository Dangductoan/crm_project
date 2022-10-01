package cybersoft.java18.crm.exception;

public class DatabaseNotFoundException extends RuntimeException {
    public DatabaseNotFoundException(String message) {
        super(message);
    }

    public DatabaseNotFoundException() {
        super();
    }
}
