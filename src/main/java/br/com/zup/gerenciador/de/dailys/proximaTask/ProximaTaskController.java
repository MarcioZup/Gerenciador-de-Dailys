package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ProximaTask salvarTask(@RequestBody @Valid ProximaTaskDTO proximaTaskEntradaDTO){
        ProximaTask proximaTask = modelMapper.map(proximaTaskEntradaDTO, ProximaTask.class);

        return proximaTaskService.salvarProximaTask(proximaTask);
    }

    @GetMapping
    public List<ProximaTaskDTO> exibirProximasTasks(){
        List<ProximaTaskDTO> listasDeProximasTasks = new ArrayList<>();

        for (ProximaTask proximaTask: proximaTaskService.exibirProximasTasks()) {
            ProximaTaskDTO proximaTaskDTO = modelMapper.map(proximaTask, ProximaTaskDTO.class);
            listasDeProximasTasks.add(proximaTaskDTO);
        }
        return listasDeProximasTasks;

    }

}
