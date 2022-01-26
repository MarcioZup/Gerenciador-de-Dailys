package br.com.zup.gerenciador.de.dailys.usuario.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO {

    private String email;
    private String senha;

}
