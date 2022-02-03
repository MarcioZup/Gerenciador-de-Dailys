package br.com.zup.gerenciador.de.dailys.impedimento.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioSaidaTasksDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImpedimentoSaidaDTO {

    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 10, max = 200, message = "Favor digitar uma descrição entre 10 a 200 caracteres")
    private String descricao;
    @NotNull
    private UsuarioSaidaTasksDTO usuario;

}
