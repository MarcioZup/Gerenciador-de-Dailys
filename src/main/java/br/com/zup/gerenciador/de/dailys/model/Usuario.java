package br.com.zup.gerenciador.de.dailys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}