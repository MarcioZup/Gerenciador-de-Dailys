package br.com.zup.gerenciador.de.dailys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProximaTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Date dataInicio;
    private Date previsaoFim;


}
