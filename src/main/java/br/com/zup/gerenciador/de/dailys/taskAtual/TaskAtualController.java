package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualEntradaDTO;
import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualSaidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/taskAtual")
public class TaskAtualController {
    @Autowired
    private  TaskAtualService taskAtualService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskAtualSaidaDTO cadastrarTaskAtual(@RequestBody @Valid TaskAtualEntradaDTO taskAtualEntradaDTO){
        TaskAtual taskAtual = taskAtualService.salvarTaskAtual(taskAtualEntradaDTO.getEmailUsuario());
        taskAtual.setDescricao(taskAtualEntradaDTO.getDescricao());
        taskAtual.setPrevisaoFim(taskAtualEntradaDTO.getPrevisaoFim());

        return modelMapper.map(taskAtual, TaskAtualSaidaDTO.class);
    }

    @GetMapping
    public List<TaskAtualEntradaDTO>exibirTaskAtuais(){
        List<TaskAtualEntradaDTO>listaDeTasksAtuais = new ArrayList<>();
        for(TaskAtual taskAtualReferencia : taskAtualService.exibirTasksAtuais()){
            TaskAtualEntradaDTO atualDTO = modelMapper.map(taskAtualReferencia, TaskAtualEntradaDTO.class);
            listaDeTasksAtuais.add(atualDTO);
        }
        return listaDeTasksAtuais;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTaskAtual(@PathVariable Long id){
        taskAtualService.deletartaskAtual(id);
    }

    @PutMapping("/{id}")
    public TaskAtualEntradaDTO atualizarTask(@PathVariable Long id, @RequestBody TaskAtualEntradaDTO taskAtualDTO){
        TaskAtual taskAtual = taskAtualService.atualizarTaskAtual(id, taskAtualDTO);

        return modelMapper.map(taskAtual, TaskAtualEntradaDTO.class);
    }
}
