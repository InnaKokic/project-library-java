package exception;
//Abstract class går ej att gör new av. Kan endast ärvas från
public abstract class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }
}
