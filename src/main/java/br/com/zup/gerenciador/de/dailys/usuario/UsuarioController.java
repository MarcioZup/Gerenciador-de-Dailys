package br.com.zup.gerenciador.de.dailys.usuario;


import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioDTO;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioFiltroDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
        Usuario novoUsuario = modelMapper.map(usuario, Usuario.class);
        modelMapper.map(usuarioService.salvarUsuario(novoUsuario), UsuarioDTO.class);
    }

    @GetMapping
    public List<UsuarioFiltroDTO> exibirUsuarios() {
        List<UsuarioFiltroDTO> listaDeUsuarios = new ArrayList<>();
        for (Usuario usuario : usuarioService.exibirUsuario()) {
            UsuarioFiltroDTO usuarioFiltroDTO = modelMapper.map(usuario, UsuarioFiltroDTO.class);
            listaDeUsuarios.add(usuarioFiltroDTO);
        }
        return listaDeUsuarios;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable String email) {
        usuarioService.deletarUsuario(email);
    }


}
