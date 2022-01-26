package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/impedimento")
public class ImpedimentoController {

    @Autowired
    private ImpedimentoService impedimentoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImpedimentoDTO cadastrarImpedimento(@RequestBody @Valid ImpedimentoDTO impedimento){
        Impedimento impedimentoACadastrar = modelMapper.map(impedimento, Impedimento.class);
        return modelMapper.map(impedimentoService.salvarImpedimento(impedimentoACadastrar),ImpedimentoDTO.class);
    }
}
