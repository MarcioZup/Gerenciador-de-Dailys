package br.com.zup.gerenciador.de.dailys.usuario;

public class DominioNaoPermitidoException extends RuntimeException {
    public DominioNaoPermitidoException(String message) {
        super(message);
    }
}
