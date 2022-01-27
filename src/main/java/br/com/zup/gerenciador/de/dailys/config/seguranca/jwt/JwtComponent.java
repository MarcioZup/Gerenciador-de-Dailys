package br.com.zup.gerenciador.de.dailys.config.seguranca.jwt;

import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.exceptions.TokenInvalidoException;
import io.jsonwebtoken.Claims;
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

    public String gerarToken(String userName, String senha){
        Date vencimento = new Date(System.currentTimeMillis()+milissegundos);

        String token = Jwts.builder().setSubject(userName)
                .claim("idUsuario", senha).setExpiration(vencimento).claim("aleatorio", "anything")
                .signWith(SignatureAlgorithm.HS512, segredo.getBytes()).compact();

        return token;
    }

    public Claims pegarClaims(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(segredo.getBytes()).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            throw new TokenInvalidoException();
        }
    }

    public boolean tokenValido(String token){
        try{
            Claims claims = pegarClaims(token);
            Date dataAtual = new Date(System.currentTimeMillis());

            String email = claims.getSubject();
            Date vencimentoToken = claims.getExpiration();

            if(email != null && vencimentoToken != null && dataAtual.before(vencimentoToken)){
                return true;
            }else {
                return false;
            }
        }catch (TokenInvalidoException e){
            return false;
        }
    }

}
