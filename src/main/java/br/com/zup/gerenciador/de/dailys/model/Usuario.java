package br.com.zup.gerenciador.de.dailys.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    private String email;
    private String senha;
    private String nome;
    @OneToMany
    private Atividade taskAtual;
    @OneToMany
    private Atividade proximaTask;
    @OneToMany
    private Atividade impedimento;

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

    public Atividade getTaskAtual() {
        return taskAtual;
    }

    public void setTaskAtual(Atividade taskAtual) {
        this.taskAtual = taskAtual;
    }

    public Atividade getProximaTask() {
        return proximaTask;
    }

    public void setProximaTask(Atividade proximaTask) {
        this.proximaTask = proximaTask;
    }

    public Atividade getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(Atividade impedimento) {
        this.impedimento = impedimento;
    }
}
