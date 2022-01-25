package br.com.zup.gerenciador.de.dailys.config.seguranca;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    private static final String[] ENDPOINT_POST_PUBLICO = {
            "/usuario",
            "/task"
    };

    private static final String[] ENDPOINT_GET_PUBLICO = {
            "/usuarios",
            "/tasks"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST, ENDPOINT_POST_PUBLICO).permitAll()
                .anyRequest().authenticated();

        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST, ENDPOINT_GET_PUBLICO).permitAll()
                .anyRequest().authenticated();
    }

}
