package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskEntradaDTO;
import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskSaidaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proximaTask")
public class ProximaTaskController {

    @Autowired
    private ProximaTaskService proximaTaskService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProximaTaskSaidaDTO salvarTask(@RequestBody @NotNull ProximaTaskEntradaDTO proximaTaskEntradaDTO){
        ProximaTask proximaTask = proximaTaskService.salvarProximaTask(proximaTaskEntradaDTO);

        return modelMapper.map(proximaTask, ProximaTaskSaidaDTO.class);

    }

    @GetMapping
    public List<ProximaTaskSaidaDTO> exibirProximasTasks(){

        List<ProximaTaskSaidaDTO> listasDeProximasTasks = new ArrayList<>();

        for (ProximaTask proximaTask: proximaTaskService.exibirProximasTasks()) {
            ProximaTaskSaidaDTO proximaTaskDTO = modelMapper.map(proximaTask, ProximaTaskSaidaDTO.class);
            listasDeProximasTasks.add(proximaTaskDTO);
        }
        return listasDeProximasTasks;

    }

    @PutMapping("/{id}")
    public ProximaTaskSaidaDTO atualizarTask (@PathVariable Long id, @RequestBody ProximaTaskSaidaDTO proximaTaskDTO){
        ProximaTask proximaTask = proximaTaskService.atualizarProximaTask(id, proximaTaskDTO);

        return modelMapper.map(proximaTask, ProximaTaskSaidaDTO.class);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProximaTask (@PathVariable Long id){

        proximaTaskService.deletarProximaTask(id);

    }

}
