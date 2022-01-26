package br.com.zup.gerenciador.de.dailys.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    public Usuario salvarUsuario (Usuario usuario){

        String senhaCriptografada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuario);

    }

    public Iterable<Usuario> exibirUsuario(){
        return usuarioRepository.findAll();

    }

    public void deletarUsuario (String email){
        usuarioRepository.deleteById(email);

    }

}
