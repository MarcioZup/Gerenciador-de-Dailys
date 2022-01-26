package br.com.zup.gerenciador.de.dailys.usuario;

public class UsuarioInexistente extends RuntimeException {
    public UsuarioInexistente(String message) {
        super(message);
    }
}
