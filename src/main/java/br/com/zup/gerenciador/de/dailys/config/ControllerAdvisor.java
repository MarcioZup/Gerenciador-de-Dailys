package br.com.zup.gerenciador.de.dailys.config;

import br.com.zup.gerenciador.de.dailys.impedimento.exception.ImpedimentoInexistente;
import br.com.zup.gerenciador.de.dailys.proximaTask.exception.ProximaTaskInexistente;
import br.com.zup.gerenciador.de.dailys.taskAtual.exception.TaskAtualInexistente;
import br.com.zup.gerenciador.de.dailys.usuario.exceptions.DominioNaoPermitidoException;
import br.com.zup.gerenciador.de.dailys.usuario.exceptions.EmailCadastrado;
import br.com.zup.gerenciador.de.dailys.usuario.exceptions.SquadNaoEncontrada;
import br.com.zup.gerenciador.de.dailys.usuario.exceptions.UsuarioInexistente;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErroValidacao> tratarErrosDeValidacao(MethodArgumentNotValidException excecao) {
        List<ErroValidacao> erros = new ArrayList<>();

        for (FieldError referencia : excecao.getFieldErrors()) {
            ErroValidacao erroValidacao = new ErroValidacao(referencia.getDefaultMessage(), referencia.getField());
            erros.add(erroValidacao);
        }
        return erros;
    }

    @ExceptionHandler(UsuarioInexistente.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> retornoUsuarioInexistente(UsuarioInexistente exception) {
        HashMap<String , String > map = new HashMap<>();
        map.put("mensagem:", exception.getMessage());
        return map;
    }

    @ExceptionHandler(DominioNaoPermitidoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HashMap<String, String> retornoDominioNaoPermitidoException(DominioNaoPermitidoException exception) {
        HashMap<String , String > map = new HashMap<>();
        map.put("mensagem:", exception.getMessage());
        return map;
    }

    @ExceptionHandler(EmailCadastrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> retornoEmailCadastrado(EmailCadastrado exception) {
        HashMap<String , String > map = new HashMap<>();
        map.put("mensagem:", exception.getMessage());
        return map;
    }

    @ExceptionHandler(SquadNaoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> retornoSquadNaoEncontrada(SquadNaoEncontrada exception) {
        HashMap<String , String > map = new HashMap<>();
        map.put("mensagem:", exception.getMessage());
        return map;
    }

    @ExceptionHandler(TaskAtualInexistente.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> retornoTaskAtualInexistente(TaskAtualInexistente exception) {
        HashMap<String , String > map = new HashMap<>();
        map.put("mensagem:", exception.getMessage());
        return map;
    }

    @ExceptionHandler(ImpedimentoInexistente.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> retornoImpedimentoInexistente(ImpedimentoInexistente exception) {
        HashMap<String , String > map = new HashMap<>();
        map.put("mensagem:", exception.getMessage());
        return map;
    }

    @ExceptionHandler(ProximaTaskInexistente.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> retornoProximaTaskInexistente(ProximaTaskInexistente exception) {
        HashMap<String , String > map = new HashMap<>();
        map.put("mensagem:", exception.getMessage());
        return map;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroValidacao manipularErroDeTipoNull(HttpMessageNotReadableException exception) {
        ErroValidacao erroValidacao = new ErroValidacao("Campo preenchido incorretamente", "tipoDeConta");

        return erroValidacao;
    }

}
