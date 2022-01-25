package br.com.zup.gerenciador.de.dailys.service;

import br.com.zup.gerenciador.de.dailys.model.Usuario;
import br.com.zup.gerenciador.de.dailys.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Usuario salvarUsuario (Usuario usuario){
        String senhaCriptograda = encoder.encode(usuario.getSenha());

        usuario.setSenha(senhaCriptograda);

        return usuarioRepository.save(usuario);
    }

    public Iterable<Usuario> exibirUsuarios(){
        return usuarioRepository.findAll();
    }

    public void deletarUsuario(String email){
        usuarioRepository.deleteById(email);
    }

}
