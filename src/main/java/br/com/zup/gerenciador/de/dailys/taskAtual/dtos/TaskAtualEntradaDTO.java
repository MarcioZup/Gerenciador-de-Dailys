package br.com.zup.gerenciador.de.dailys.taskAtual.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAtualEntradaDTO {
    @NotBlank
    @Size(min = 10, max = 200, message = "Favor digitar uma descrição entre 10 a 200 caracteres")
    private String descricao;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dataInicio = LocalDate.now();
    @DateTimeFormat
    private String previsaoFim;
    @NotBlank
    private String emailUsuario;

}
