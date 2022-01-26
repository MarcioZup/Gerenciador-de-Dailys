package br.com.zup.gerenciador.de.dailys.impedimento.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImpedimentoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String descricao;

    @ManyToOne(optional = false)
    private Usuario usuario;

}
