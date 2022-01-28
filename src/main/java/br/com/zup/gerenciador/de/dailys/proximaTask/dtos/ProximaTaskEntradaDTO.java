package br.com.zup.gerenciador.de.dailys.proximaTask.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class ProximaTaskEntradaDTO {

    @NotNull
    @Size(min = 10, max = 200, message = "Favor digitar uma descrição entre 10 a 200 caracteres")
    private String descricao;
    @NotNull
    private Date dataInicio;
    @NotNull
    private Date previsaoFim;
    @NotNull
    private String emailUsuario;
}
