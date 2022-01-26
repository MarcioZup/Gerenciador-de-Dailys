package br.com.zup.gerenciador.de.dailys.impedimento;

import br.com.zup.gerenciador.de.dailys.impedimento.dtos.ImpedimentoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public List<ImpedimentoDTO> exibirImpedimentos(){
        List<ImpedimentoDTO> listaDeImpedimentos = new ArrayList<>();
        for(Impedimento impedimentoReferencia: impedimentoService.exibirImpedimentos()){
            ImpedimentoDTO impedimentoDTO = modelMapper.map(impedimentoReferencia, ImpedimentoDTO.class);
            listaDeImpedimentos.add(impedimentoDTO);
        }
        return listaDeImpedimentos;
    }

    @PutMapping
    public ImpedimentoDTO atualizarImpedimento(@RequestBody ImpedimentoDTO impedimentoDTO){
        Impedimento impedimento = modelMapper.map(impedimentoDTO, Impedimento.class);
        return modelMapper.map(impedimentoService.atualizarImpedimento(impedimento), ImpedimentoDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarImpedimento(@PathVariable Long id){
        impedimentoService.deletarImpedimento(id);
    }
}
