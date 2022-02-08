package br.com.zup.gerenciador.de.dailys.usuario;

import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioDTO;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioFiltroDTO;
import br.com.zup.gerenciador.de.dailys.usuario.exception.DominioNaoPermitidoException;
import br.com.zup.gerenciador.de.dailys.usuario.exception.EmailCadastrado;
import br.com.zup.gerenciador.de.dailys.usuario.exception.SquadNaoEncontrada;
import br.com.zup.gerenciador.de.dailys.usuario.exception.UsuarioInexistente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    ModelMapper modelMapper;

    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        if (usuarioRepository.countByEmail(usuario.getEmail()) > 0) {
            throw new EmailCadastrado("Email já cadastrado");
        } else {
            String senhaCriptografada = encoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);

            return usuarioRepository.save(usuario);
        }
    }

    public void deletarUsuario(String email) {
        if (!usuarioRepository.existsById(email)) {
            throw new UsuarioInexistente("Usuario não encontrado");
        }
        usuarioRepository.deleteById(email);
    }

    public void validarEmail(String email) {
        if (email.contains("@zup.com.br")) {
            if (usuarioRepository.countByEmail(email) > 0) {
                throw new EmailCadastrado("Email já cadastrado");
            }
        }else throw new DominioNaoPermitidoException("Esse domínio não tem acesso permitido a esse sistema");
    }

    public Usuario exibirUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarioRepository.findAll()) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        throw new UsuarioInexistente("Usuário não encontrado");
    }

    public List<UsuarioFiltroDTO> exibirUsuarioPorSquad(String nomeDaSquad){

        List<Usuario> usuarios = usuarioRepository.findByNomeDaSquad(nomeDaSquad);
        List<UsuarioFiltroDTO> usuarioFiltroDTOList = new ArrayList<>();

        if (usuarios.isEmpty()){
            throw new SquadNaoEncontrada("Não foi encontrado Squad com este nome");
        }

        for (Usuario usuario : usuarios){
            usuarioFiltroDTOList.add(modelMapper.map(usuario, UsuarioFiltroDTO.class));
        }

        return usuarioFiltroDTOList;

    }

    public Usuario alterarDadosUsuario(String email, UsuarioDTO usuarioNovo) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()){
            throw new UsuarioInexistente("Usuário não encontrado");
        }
        Usuario usuarioParaAtualizar = usuario.get();
        usuarioParaAtualizar.setNomeDaSquad(usuarioNovo.getNomeDaSquad());

        usuarioRepository.save(usuarioParaAtualizar);

        return usuarioParaAtualizar;
    }

}

