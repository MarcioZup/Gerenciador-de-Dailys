package br.com.zup.gerenciador.de.dailys.config.seguranca.jwt;

import org.springframework.security.authentication.AuthenticationManager;
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

}
