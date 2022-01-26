package br.com.zup.gerenciador.de.dailys.impedimento.dtos;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImpedimentoDTO {


    private String descricao;

    private Usuario usuario;

}
