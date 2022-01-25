package br.com.zup.gerenciador.de.dailys.config.seguranca.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class FiltroDeAutenticacaoJwt extends UsernamePasswordAuthenticationFilter {

    private JwtComponent jwtComponent;
    private AuthenticationManager authenticationManager;

    public FiltroDeAutenticacaoJwt(JwtComponent jwtComponent, AuthenticationManager authenticationManager) {
        this.jwtComponent = jwtComponent;
        this.authenticationManager = authenticationManager;
    }



}
