package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProximaTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataInicio = LocalDate.now();
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String previsaoFim;

    @ManyToOne(optional = false)
    private Usuario usuario;

}
