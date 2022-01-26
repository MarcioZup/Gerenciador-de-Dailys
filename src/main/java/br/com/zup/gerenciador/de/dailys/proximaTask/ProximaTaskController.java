package br.com.zup.gerenciador.de.dailys.proximaTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proximaTask")
public class ProximaTaskController {

    @Autowired
    private ProximaTaskService proximaTaskService;

}
