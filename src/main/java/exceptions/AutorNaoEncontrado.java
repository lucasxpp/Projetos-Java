package exceptions;

public class AutorNaoEncontrado extends RuntimeException {
    public AutorNaoEncontrado(String message) {
        super(message);
    }
}
