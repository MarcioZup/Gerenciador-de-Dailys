package br.com.zup.gerenciador.de.dailys.proximaTask.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsarioSaidaTasksDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProximaTaskSaidaDTO {

    private Long id;
    @NotBlank
    @Size(min = 10, max = 200, message = "Favor digitar uma descrição entre 10 a 200 caracteres")
    private String descricao;
    @NotNull
    private Date dataInicio;
    @NotNull
    private Date previsaoFim;
    @Valid
    private UsarioSaidaTasksDTO usuario;

}
