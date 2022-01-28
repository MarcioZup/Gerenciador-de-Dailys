package br.com.zup.gerenciador.de.dailys.proximaTask.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class ProximaTaskEntradaDTO {

    @NotNull
    @Size(min = 10, max = 200, message = "Favor digitar uma descrição entre 10 a 200 caracteres")
    private String descricao;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate dataInicio = LocalDate.now();
    @NotNull
    private String previsaoFim;
    @NotNull
    private String emailUsuario;
}
