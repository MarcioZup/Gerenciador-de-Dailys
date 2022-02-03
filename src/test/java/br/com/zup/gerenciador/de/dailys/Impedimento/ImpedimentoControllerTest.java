package br.com.zup.gerenciador.de.dailys.Impedimento;

import br.com.zup.gerenciador.de.dailys.config.componentes.Conversor;
import br.com.zup.gerenciador.de.dailys.config.seguranca.UsuarioLoginService;
import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.JwtComponent;
import br.com.zup.gerenciador.de.dailys.impedimento.Impedimento;
import br.com.zup.gerenciador.de.dailys.impedimento.ImpedimentoInexistente;
import br.com.zup.gerenciador.de.dailys.impedimento.ImpedimentoService;
import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoEntradaDTO;
import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoSaidaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.ProximaTask;
import br.com.zup.gerenciador.de.dailys.proximaTask.ProximaTaskController;
import br.com.zup.gerenciador.de.dailys.proximaTask.ProximaTaskService;
import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskEntradaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskSaidaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.exception.ProximaTaskInexistente;
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

import java.time.LocalDate;

@WebMvcTest({ImpedimentoControllerTest.class, Conversor.class, UsuarioLoginService.class, JwtComponent.class})
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

    @Mock
    private ImpedimentoSaidaDTO impedimentoSaidaDTO;
    @Mock
    private ImpedimentoEntradaDTO impedimentoEntradaDTO;


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
    public void testarDeletarImpedimentoNaoEncontrado() throws Exception {
        Mockito.doThrow(ImpedimentoInexistente.class).when(impedimentoService).deletarImpedimento(Mockito.anyLong());

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.delete("/impedimento/" + impedimento.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

}
