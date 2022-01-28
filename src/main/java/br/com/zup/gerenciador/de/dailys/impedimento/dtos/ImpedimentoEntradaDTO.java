package br.com.zup.gerenciador.de.dailys.impedimento.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
@Setter
public class ImpedimentoEntradaDTO {

    @NotNull
    @Size(min = 10, max = 200, message = "Favor digitar uma descrição entre 10 a 200 caracteres")
    private String descricao;
    @NotNull
    private String emailUsuario;

}
