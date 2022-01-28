package br.com.zup.gerenciador.de.dailys.proximaTask.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsarioSaidaTasksDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate dataInicio = LocalDate.now();
    @NotNull
    private String previsaoFim;
    @Valid
    private UsarioSaidaTasksDTO usuario;

}
