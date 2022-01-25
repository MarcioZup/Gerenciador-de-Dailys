package br.com.zup.gerenciador.de.dailys.config.seguranca.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtComponent {

    @Value("${jwt.segredo}")
    private String segredo;
    @Value("${jwt.milissegundos}")
    private Long milissegundos;

    public String gerarToken(String email, String senha){
        Date vencimento = new Date(System.currentTimeMillis()+milissegundos);

        String token = Jwts.builder().setSubject(email)
                .claim("idUsuario", senha).setExpiration(vencimento)
                .signWith(SignatureAlgorithm.HS512, segredo.getBytes()).compact();

        return token;
    }

}
