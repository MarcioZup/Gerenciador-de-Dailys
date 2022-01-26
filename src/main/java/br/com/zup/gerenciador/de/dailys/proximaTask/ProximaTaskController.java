package br.com.zup.gerenciador.de.dailys.proximaTask;

import br.com.zup.gerenciador.de.dailys.proximaTask.dtos.ProximaTaskEntradaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/proximaTask")
public class ProximaTaskController {

    @Autowired
    private ProximaTaskService proximaTaskService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProximaTask salvarTask(@RequestBody @Valid ProximaTaskEntradaDTO proximaTaskEntradaDTO){
        ProximaTask proximaTask = modelMapper.map(proximaTaskEntradaDTO, ProximaTask.class);

        return proximaTaskService.salvarProximaTask(proximaTask);
    }

}
