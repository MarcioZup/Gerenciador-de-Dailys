package br.com.zup.gerenciador.de.dailys;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioRepository;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UsuarioServiceTest {
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    private void setup(){
        usuario = new Usuario();
        usuario.setNome("Márcio Viana");
        usuario.setEmail("marcio.viana@zup.com.br");
        usuario.setSenha("123456");
        usuario.setNomeDaSquad("ItauCard");
    }

    @Test
    public void testarSalvarUsuarioCaminhoRuim(){
        Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(false);

        Assertions.assertThrows(UsuarioInexistente.class, () -> {usuarioService.salvarUsuario(usuario);});
    }

    @Test
    public void testarSalvarUsuarioCaminhoBom(){
    Mockito.when(usuarioRepository.existsById(Mockito.anyString())).thenReturn(true);

    usuarioRepository.save(usuario);
    Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuario);
    }

    @Test
    public void testarDeletarUsuario(){

    }

}
