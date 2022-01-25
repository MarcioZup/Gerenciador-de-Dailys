package br.com.zup.gerenciador.de.dailys.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    private String email;
    private String senha;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)
    private TaskAtual taskAtual;
    @OneToMany(cascade = CascadeType.ALL)
    private TaskAtual proximaTask;
    @OneToMany(cascade = CascadeType.ALL)
    private TaskAtual impedimento;

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TaskAtual getTaskAtual() {
        return taskAtual;
    }

    public void setTaskAtual(TaskAtual taskAtual) {
        this.taskAtual = taskAtual;
    }

    public TaskAtual getProximaTask() {
        return proximaTask;
    }

    public void setProximaTask(TaskAtual proximaTask) {
        this.proximaTask = proximaTask;
    }

    public TaskAtual getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(TaskAtual impedimento) {
        this.impedimento = impedimento;
    }
}
