package exceptions;

public class EmprestimoNaoEncontrado extends RuntimeException {
    public EmprestimoNaoEncontrado(String message) {
        super(message);
    }
}
