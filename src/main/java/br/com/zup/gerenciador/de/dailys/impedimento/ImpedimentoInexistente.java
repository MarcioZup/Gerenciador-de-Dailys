package br.com.zup.gerenciador.de.dailys.impedimento;

public class ImpedimentoInexistente extends RuntimeException {
    public ImpedimentoInexistente(String message) {
        super(message);
    }
}
