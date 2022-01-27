package br.com.zup.gerenciador.de.dailys.proximaTask.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProximaTaskDTO {

    @NotBlank
    private String descricao;
    @NotNull
    private Date dataInicio;
    @NotNull
    private Date previsaoFim;
    @Valid
    @NotBlank
    private Usuario usuario;

}
