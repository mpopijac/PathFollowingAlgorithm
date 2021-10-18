package hr.mpopijac.exceptions;

public class ReadFromFileException extends Exception {

    public ReadFromFileException(String message, Exception e) {
        super(message, e);
    }
}
