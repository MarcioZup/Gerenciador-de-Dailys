package br.com.zup.gerenciador.de.dailys.configuracao.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {
    private static final String[] ENDPOINT_POST_PUBLICO = {
            "/usuario",
            "/tasks"
    };

    private static final String[] ENDPOINT_GET_PUBLICO = {
            "/tasks"
    };

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST, ENDPOINT_POST_PUBLICO).permitAll()
                .anyRequest().authenticated();

        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET, ENDPOINT_GET_PUBLICO).permitAll()
                .anyRequest().authenticated();

    }

}
