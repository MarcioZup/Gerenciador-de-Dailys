package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.config.componentes.Conversor;
import br.com.zup.gerenciador.de.dailys.config.seguranca.UsuarioLoginService;
import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.JwtComponent;
import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoEntradaDTO;
import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoSaidaDTO;
import br.com.zup.gerenciador.de.dailys.impedimento.exception.ImpedimentoInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;


@WebMvcTest({ImpedimentoController.class, Conversor.class, UsuarioLoginService.class, JwtComponent.class})
public class ImpedimentoControllerTest {
    @MockBean
    ImpedimentoInexistente impedimentoInexistente;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JwtComponent jwtComponent;
    @MockBean
    ImpedimentoService impedimentoService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ImpedimentoSaidaDTO impedimentoDTO;

    @Mock
    private Impedimento impedimento;
    @Mock
    private Usuario usuario;

    @BeforeEach
    public void setup() {
        usuario = new Usuario();
        usuario.setEmail("karen.almeida@zup.com.br");
        usuario.setSenha("Xablau123");
        usuario.setNome("karen");
        usuario.setNomeDaSquad("Vivo");

        impedimento = new Impedimento();
        impedimento.setId(Long.valueOf(1));
        impedimento.setDescricao("Anything");
        impedimento.setUsuario(usuario);

        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser("karen.almeida@zup.com.br")
    public void testarDeletarImpedimentoNegativo() throws Exception {
        doThrow(ImpedimentoInexistente.class).when(impedimentoService).deletarImpedimento(anyLong());

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.delete("/impedimento/" + impedimento.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @WithMockUser("karen.almeida@zup.com.br")
    public void testarDeletarImpedimentoPositivo() throws Exception {
        impedimento.setId(Long.valueOf(1));
        doNothing().when(impedimentoService).deletarImpedimento(anyLong());
        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.delete("/impedimento/" + impedimento.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(204));

        verify(impedimentoService, times(1)).deletarImpedimento(anyLong());
    }

    @Test
    @WithMockUser("karen.almeida@zup.com.br")
    public void testarAlterarDescricaoImpedimentoNegativo() throws Exception {
        doThrow(ImpedimentoInexistente.class).when(impedimentoService)
                .alterarDescricaoImpedimento(anyLong(), any(ImpedimentoSaidaDTO.class));
        String json = objectMapper.writeValueAsString(impedimentoDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.put("/impedimento/1")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));

    }

    @Test
   @WithMockUser("karen.almeida@zup.com.br")
    public void testarRotaParaCadastroImpedimentoNegativo() throws Exception {
        Mockito.when(impedimentoService.salvarImpedimento(Mockito.any(ImpedimentoEntradaDTO.class))).thenReturn(impedimento);
        String json = objectMapper.writeValueAsString(impedimentoDTO);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.post("/impedimento")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(422));

        String jsonDeRespostaDaAPI = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
    }

@Test
    @WithMockUser("karen.almeida@zup.com.br")
    public void testarRotaParaCadastroImpedimentoPositivo() throws Exception {
        Mockito.when(impedimentoService.salvarImpedimento(Mockito.any(ImpedimentoEntradaDTO.class))).thenReturn(impedimento);
        String json = objectMapper.writeValueAsString(impedimento);

        ResultActions respostaDaRequisicao = mockMvc.perform(MockMvcRequestBuilders.post("/impedimento")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));

        String jsonDeResposta = respostaDaRequisicao.andReturn().getResponse().getContentAsString();

    }

}


