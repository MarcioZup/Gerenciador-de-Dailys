package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAtual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate dataInicio = LocalDate.now();
    private String previsaoFim;

    @ManyToOne(optional = false)
    private Usuario usuario;

}
