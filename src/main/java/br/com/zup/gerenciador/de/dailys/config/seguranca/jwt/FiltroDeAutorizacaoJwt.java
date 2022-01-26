package br.com.zup.gerenciador.de.dailys.config.seguranca.jwt;

import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.exceptions.TokenInvalidoException;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class FiltroDeAutorizacaoJwt extends BasicAuthenticationFilter {

    private JwtComponent jwtComponent;
    private UserDetailsService userDetailsService;

    public FiltroDeAutorizacaoJwt(AuthenticationManager authenticationManager, JwtComponent jwtComponent,
                                  UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtComponent = jwtComponent;
        this.userDetailsService = userDetailsService;

    }

    public UsernamePasswordAuthenticationToken pegarAutenticacao(String token){
        if(!jwtComponent.tokenValido(token)){
            throw new TokenInvalidoException();

        }

        Claims claims = jwtComponent.pegarClaims(token);
        UserDetails usuarioLogado = userDetailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(usuarioLogado, null, usuarioLogado.getAuthorities());

    }

}
