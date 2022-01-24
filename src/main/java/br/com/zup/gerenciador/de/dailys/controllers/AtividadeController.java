package br.com.zup.gerenciador.de.dailys.controllers;

import br.com.zup.gerenciador.de.dailys.services.AtividadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private ModelMapper modelMapper;

}
