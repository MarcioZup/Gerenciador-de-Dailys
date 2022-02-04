package br.com.zup.gerenciador.de.dailys.taskAtual;

public class TaskAtualInexistente extends RuntimeException {
    public TaskAtualInexistente(String message) {
        super(message);
    }
}
