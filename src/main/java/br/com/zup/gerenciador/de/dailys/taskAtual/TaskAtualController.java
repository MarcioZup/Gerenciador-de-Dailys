package br.com.zup.gerenciador.de.dailys.taskAtual;

import br.com.zup.gerenciador.de.dailys.taskAtual.dtos.TaskAtualDTO;
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
    public TaskAtualDTO cadastrarTaskAtual(@RequestBody @Valid TaskAtualDTO taskAtual){
        TaskAtual taskAtualParaCadastro = modelMapper.map(taskAtual, TaskAtual.class);
        return modelMapper.map(taskAtualService.salvarTaskAtual(taskAtualParaCadastro),TaskAtualDTO.class);
    }

    @GetMapping
    public List<TaskAtualDTO>exibirTaskAtuais(){
        List<TaskAtualDTO>listaDeTasksAtuais = new ArrayList<>();
        for(TaskAtual taskAtualReferencia : taskAtualService.exibirTasksAtuais()){
            TaskAtualDTO atualDTO = modelMapper.map(taskAtualReferencia, TaskAtualDTO.class);
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
    public TaskAtualDTO atualizarTask(@PathVariable Long id, @RequestBody TaskAtualDTO taskAtualDTO){
        TaskAtual taskAtual = taskAtualService.atualizarTaskAtual(id, taskAtualDTO);

        return modelMapper.map(taskAtual, TaskAtualDTO.class);
    }
}
