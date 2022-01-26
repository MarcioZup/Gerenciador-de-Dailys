package br.com.zup.gerenciador.de.dailys.taskAtual.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

public class TaskAtualDTO {
    @Entity

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class TaskAtual {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String descricao;
        private Date dataInicio;
        private Date previsaoFim;

        @ManyToOne(optional = false)
        private Usuario usuario;

    }
}
