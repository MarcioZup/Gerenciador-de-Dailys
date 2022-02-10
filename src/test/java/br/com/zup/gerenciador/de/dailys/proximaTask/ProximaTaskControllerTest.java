package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.config.componentes.Conversor;
import br.com.zup.gerenciador.de.dailys.config.seguranca.UsuarioLoginService;
import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.JwtComponent;
import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskEntradaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskSaidaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.exception.ProximaTaskInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@WebMvcTest({ProximaTaskController.class, Conversor.class, UsuarioLoginService.class, JwtComponent.class})
public class  ProximaTaskControllerTest {

    @MockBean
    private ProximaTaskService proximaTaskService;
    @MockBean
    private UsuarioLoginService usuarioLoginService;
    @MockBean
    private JwtComponent jwtComponent;

    @Autowired
    MockMvc mockMvc;

    @Mock
    private ProximaTaskSaidaDTO proximaTaskSaidaDTO;
    @Mock
    private ProximaTaskEntradaDTO proximaTaskEntradaDTO;
    @Mock
    private ProximaTask proximaTask;
    @Mock
    private Usuario usuario;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        usuario = new Usuario();
        usuario.setEmail("let.let@zup.com.br");
        usuario.setSenha("Xablau123");
        usuario.setNome("Let Marçal Matias");
        usuario.setNomeDaSquad("Vivo");

        proximaTask = new ProximaTask();
        proximaTask.setId(Long.valueOf(1));
        proximaTask.setDescricao("Anything");
        proximaTask.setDataInicio(LocalDate.now());
        proximaTask.setPrevisaoFim("01-02-2022");
        proximaTask.setUsuario(usuario);

        proximaTaskEntradaDTO = new ProximaTaskEntradaDTO();
        proximaTaskEntradaDTO.setDescricao("");
        proximaTaskEntradaDTO.setDataInicio(LocalDate.of(2022,02,11));
        proximaTaskEntradaDTO.setPrevisaoFim("20-03-2022");
        proximaTaskEntradaDTO.setEmailUsuario("let.let@zup.com.br");

        proximaTaskSaidaDTO = new ProximaTaskSaidaDTO();
        proximaTaskSaidaDTO.getId();
        proximaTaskSaidaDTO.getDescricao();
        proximaTaskSaidaDTO.getDataInicio();
        proximaTaskSaidaDTO.getPrevisaoFim();
        proximaTaskSaidaDTO.getUsuario();


        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
    }

    @Test
    @WithMockUser("let.let@zup.com.br")
    public void testeDeletarProximaTaskNegativo() throws Exception {
        Mockito.doThrow(ProximaTaskInexistente.class).when(proximaTaskService).deletarProximaTask(Mockito.anyLong());

        ResultActions resposta = mockMvc.perform(MockMvcRequestBuilders.delete("/proximaTask" +
                        proximaTask.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @WithMockUser("let.let@zup.com.br")
    public void testarDeletarProximaTaskPositivo() throws Exception {
        proximaTask.setId(Long.valueOf(1));
        doNothing().when(proximaTaskService).deletarProximaTask(anyLong());
        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.delete("/proximaTask/" +
                                proximaTask.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(204));

        verify(proximaTaskService, times(1)).deletarProximaTask(anyLong());
    }

    @Test
    @WithMockUser("karen.almeida@zup.com.br")
    public void testarAtualizarProximaTaskNegativo() throws Exception {
        doThrow(ProximaTaskInexistente.class).when(proximaTaskService)
                .atualizarProximaTask(anyLong(), any(ProximaTaskSaidaDTO.class));
        String json = objectMapper.writeValueAsString(proximaTaskSaidaDTO);

        ResultActions resultado = mockMvc.perform(MockMvcRequestBuilders.put("/proximaTask/1")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));

    }

}