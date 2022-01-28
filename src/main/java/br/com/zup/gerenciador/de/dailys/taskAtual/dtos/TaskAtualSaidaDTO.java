package br.com.zup.gerenciador.de.dailys.taskAtual.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsarioSaidaTasksDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class TaskAtualSaidaDTO {
    private Long id;
    @NotBlank
    @Size(min = 10, max = 200, message = "Favor digitar uma descrição entre 10 a 200 caracteres")
    private String descricao;
    @NotNull
    private Date dataInicio;
    @NotNull
    private Date previsaoFim;
    @NotBlank
    private UsarioSaidaTasksDTO usuario;
}
