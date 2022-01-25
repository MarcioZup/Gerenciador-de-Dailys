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
    @OneToMany(cascade = CascadeType.MERGE)
    private List<TaskAtual> tasksAtuais;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<ProximaTask> proximaTasks;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Impedimento> impedimentos;



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

    public List<TaskAtual> getTasksAtuais() {
        return tasksAtuais;
    }

    public void setTasksAtuais(List<TaskAtual> tasksAtuais) {
        this.tasksAtuais = tasksAtuais;
    }

    public List<ProximaTask> getProximaTasks() {
        return proximaTasks;
    }

    public void setProximaTasks(List<ProximaTask> proximaTasks) {
        this.proximaTasks = proximaTasks;
    }

    public List<Impedimento> getImpedimentos() {
        return impedimentos;
    }

    public void setImpedimentos(List<Impedimento> impedimentos) {
        this.impedimentos = impedimentos;
    }
}
