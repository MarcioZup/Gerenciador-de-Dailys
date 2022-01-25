package br.com.zup.gerenciador.de.dailys.service;

import br.com.zup.gerenciador.de.dailys.model.Usuario;
import br.com.zup.gerenciador.de.dailys.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario (Usuario usuario){
        return usuarioRepository.save(usuario);

    }

    public Iterable<Usuario> exibirUsuario(){
        return usuarioRepository.findAll();

    }


}
