package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    private Date dataInicio;
    private Date previsaoFim;

    @ManyToOne(optional = false)
    private Usuario usuario;

}