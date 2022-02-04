package br.com.zup.gerenciador.de.dailys.usuario.exception;

public class EmailCadastrado extends RuntimeException {
    public EmailCadastrado(String message) {
        super(message);
    }
}
