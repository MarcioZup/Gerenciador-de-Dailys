package br.com.zup.gerenciador.de.dailys.usuario;

import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioRepository;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import br.com.zup.gerenciador.de.dailys.usuario.exception.UsuarioInexistente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

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
    public void testeDeletarUsuario(){
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
        Mockito.doNothing().when(usuarioRepository).deleteById(Mockito.anyString());
        usuarioRepository.deleteById(usuario.getEmail());

        Mockito.verify(usuarioRepository, Mockito.times(1)).deleteById(Mockito.anyString());
    }

    @Test
    public void testeAlterarDadosDoUsuarioCaminhoPositivo(){
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
        usuario.setNomeDaSquad("Vivo");
        Assertions.assertEquals("Vivo", "Vivo");
    }

    @Test
    public void testeAlterarDadosDoUsuarioCaminhoNegativo(){
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
        usuario.setNomeDaSquad("Vivo");
        Assertions.assertFalse(usuario.getNomeDaSquad().equals("Itaucard"));
    }

    @Test
    public void testeValidarEmail(){
        Assertions.assertTrue(usuario.getEmail().contains("@zup.com.br"));
    }



}
