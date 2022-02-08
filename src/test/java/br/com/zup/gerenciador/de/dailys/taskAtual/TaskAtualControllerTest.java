package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.config.componentes.Conversor;
import br.com.zup.gerenciador.de.dailys.config.seguranca.UsuarioLoginService;
import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.JwtComponent;
import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualEntradaDTO;
import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualSaidaDTO;
import br.com.zup.gerenciador.de.dailys.taskAtual.exception.TaskAtualInexistente;
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

@WebMvcTest({TaskAtualController.class, Conversor.class, UsuarioLoginService.class, JwtComponent.class})
public class TaskAtualControllerTest {

    @MockBean
    private TaskAtualService taskAtualService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JwtComponent jwtComponent;

    @Autowired
    MockMvc mockMvc;

    @Mock
    private TaskAtualSaidaDTO taskAtualSaidaDTO;
    @Mock
    private TaskAtualEntradaDTO taskAtualEntradaDTO;

    private ObjectMapper objectMapper;
    @Mock
    private TaskAtual taskAtual;
    @Mock
    private Usuario usuario;

    @BeforeEach
    public void setup() {
        usuario = new Usuario();
        usuario.setEmail("let.let@zup.com.br");
        usuario.setSenha("Xablau123");
        usuario.setNome("Let Marçal Matias");
        usuario.setNomeDaSquad("Vivo");

        taskAtual = new TaskAtual();
        taskAtual.setId(Long.valueOf(1));
        taskAtual.setDescricao("Anything");
        taskAtual.setDataInicio(LocalDate.now());
        taskAtual.setPrevisaoFim("01-02-2022");
        taskAtual.setUsuario(usuario);

        taskAtualEntradaDTO = new TaskAtualEntradaDTO();
        taskAtualEntradaDTO.setDescricao("");
        taskAtualEntradaDTO.setDataInicio(LocalDate.now());
        taskAtualEntradaDTO.setPrevisaoFim("20-03-2022");
        taskAtualEntradaDTO.setEmailUsuario("let.let@zup.com.br");

        taskAtualEntradaDTO = new TaskAtualEntradaDTO();
        taskAtualEntradaDTO.getDescricao();
        taskAtualEntradaDTO.getDataInicio();
        taskAtualEntradaDTO.getPrevisaoFim();



        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser("let.let@zup.com.br")
    public void testeDeletarTaskAtualNegativo() throws Exception {
        Mockito.doThrow(TaskAtualInexistente.class).when(taskAtualService).deletartaskAtual(Mockito.anyLong());

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.delete("/proximaTask" +
                        taskAtual.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @WithMockUser("marcio.viana@zup.com.br")
    public void testarDeletarTaskAtual() throws Exception {
        taskAtual.setId(Long.valueOf(1));
        Mockito.doNothing().when(taskAtualService).deletartaskAtual(Mockito.anyLong());
        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.delete("/taskAtual/" + taskAtual.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(204));

        Mockito.verify(taskAtualService, Mockito.times(1)).deletartaskAtual(Mockito.anyLong());
    }
}