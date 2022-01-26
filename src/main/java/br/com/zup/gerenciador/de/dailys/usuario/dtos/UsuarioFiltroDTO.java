package br.com.zup.gerenciador.de.dailys.usuario.dtos;

import br.com.zup.gerenciador.de.dailys.impedimento.Impedimento;
import br.com.zup.gerenciador.de.dailys.proximaTask.ProximaTask;
import br.com.zup.gerenciador.de.dailys.taskAtual.TaskAtual;
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

public class UsuarioFiltroDTO {
    @Id
    private String email;
    private String nome;
    private String nomeDaSquad;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<TaskAtual> tasksAtuais;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<ProximaTask> proximaTasks;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Impedimento> impedimentos;
}
