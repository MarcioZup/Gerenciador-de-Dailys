package br.com.zup.gerenciador.de.dailys.usuario;


import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioDTO;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioFiltroDTO;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.image.VolatileImage;
import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
        usuarioService.validarEmail(usuario.getEmail());
        Usuario novoUsuario = modelMapper.map(usuario, Usuario.class);
        modelMapper.map(usuarioService.salvarUsuario(novoUsuario), UsuarioDTO.class);
    }

    @GetMapping("/{email}")
    public UsuarioFiltroDTO exibirUsuarioPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.exibirUsuarioPorEmail(email);
        return modelMapper.map(usuario, UsuarioFiltroDTO.class);
    }

    @GetMapping("squad/{nomeDaSquad}")
    public List<UsuarioFiltroDTO> exibirUsuarioPorSquad(@PathVariable String nomeDaSquad) {

        List<UsuarioFiltroDTO> usuarios = usuarioService.exibirUsuarioPorSquad(nomeDaSquad);

        return usuarios;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable String email) {
        usuarioService.deletarUsuario(email);
    }

    @PutMapping("/{email}")
    public UsuarioFiltroDTO alterarDadosUsuario(@PathVariable String email,
                                                @RequestBody UsuarioDTO usuarioDTO){
            Usuario usuario = usuarioService.alterarDadosUsuario(email, usuarioDTO);

            return modelMapper.map(usuario,UsuarioFiltroDTO.class);

    }

}
