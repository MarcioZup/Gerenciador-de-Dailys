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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class UsuarioDTO {
    @Id
    @Email
    @NotBlank
    private String email;
    private String senha;
    @NotNull
    @Size(min = 2, max = 20, message = "Favor digitar um nome entre 2 a 20 caracteres")
    private String nome;
    @NotBlank
    private String nomeDaSquad;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<TaskAtual> tasksAtuais;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<ProximaTask> proximaTasks;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Impedimento> impedimentos;
}
