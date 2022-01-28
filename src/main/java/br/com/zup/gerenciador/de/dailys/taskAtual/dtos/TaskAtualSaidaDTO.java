package br.com.zup.gerenciador.de.dailys.taskAtual.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsarioSaidaTasksDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class TaskAtualSaidaDTO {
    private Long id;
    @NotBlank
    @Size(min = 10, max = 200, message = "Favor digitar uma descrição entre 10 a 200 caracteres")
    private String descricao;
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate dataInicio = LocalDate.now();
    @NotNull
    @DateTimeFormat
    private String previsaoFim;
    @NotBlank
    private UsarioSaidaTasksDTO usuario;

}
