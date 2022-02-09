package br.com.zup.gerenciador.de.dailys.usuario;

import br.com.zup.gerenciador.de.dailys.config.componentes.Conversor;
import br.com.zup.gerenciador.de.dailys.config.seguranca.UsuarioLogado;
import br.com.zup.gerenciador.de.dailys.config.seguranca.UsuarioLoginService;
import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.JwtComponent;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioController;
import br.com.zup.gerenciador.de.dailys.usuario.UsuarioService;
import br.com.zup.gerenciador.de.dailys.usuario.dtos.UsuarioDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest({UsuarioController.class, Conversor.class, UsuarioLoginService.class, JwtComponent.class})
public class UsuarioControllerTest {
    @MockBean
    private UsuarioService usuarioService;
    @Mock
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JwtComponent jwtComponent;
    @Mock
    UsuarioLogado usuarioLogado;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    public void setup() {

        usuario = new Usuario();
        usuario.setNome("Márcio Viana");
        usuario.setEmail("marcio.viana@zup.com.br");
        usuario.setSenha("mfv1234");
        usuario.setNomeDaSquad("Itau");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("Márcio Viana");
        usuarioDTO.setEmail("marcio.viana@zup.com.br");
        usuarioDTO.setSenha("mfv1234");
        usuarioDTO.setNomeDaSquad("Itau");

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testarRotaParaCadastrarUsuario() throws Exception {
        Mockito.when(usuarioService.salvarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);
        String json = objectMapper.writeValueAsString(usuarioDTO);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));

        String jsonDeRespostaDaAPI = respostaDaRequisicao.andReturn().getResponse().getContentAsString();

    }

}