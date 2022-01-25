package br.com.zup.gerenciador.de.dailys.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TaskAtual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Date dataInicio;
    private Date previsaoFim;

    public TaskAtual() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getPrevisaoFim() {
        return previsaoFim;
    }

    public void setPrevisaoFim(Date previsaoFim) {
        this.previsaoFim = previsaoFim;
    }

    @ManyToOne(optional = false)
    private Usuario usuarios;

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }
}
