package br.com.zup.gerenciador.de.dailys.config.seguranca;

import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.FiltroDeAutenticacaoJwt;
import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.FiltroDeAutorizacaoJwt;
import br.com.zup.gerenciador.de.dailys.config.seguranca.jwt.JwtComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtComponent jwtComponent;
    @Autowired
    private UserDetailsService userDetailsService;

    private static final String[] ENDPOINT_POST_PUBLICO = {
            "/usuario",
            "/proximaTask",
            "/taskAtual",
            "/impedimento"
    };

    private static final String[] ENDPOINT_GET_PUBLICO = {
            "/usuario",
            "/taskAtual",
            "/proximaTask",
            "/impedimento"
    };

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();
        httpSecurity.cors().configurationSource(configuracaoDoCORS());

        httpSecurity.authorizeRequests()
                .antMatchers((AUTH_WHITELIST)).permitAll()
                .antMatchers(HttpMethod.POST, ENDPOINT_POST_PUBLICO).permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources",
                        "/swagger-resources/configuration/security", "/swagger-ui/**", "/webjars/**").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.GET, ENDPOINT_GET_PUBLICO)
                .permitAll().anyRequest().authenticated();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilter(new FiltroDeAutenticacaoJwt(jwtComponent, authenticationManager()));

        httpSecurity.addFilter(new FiltroDeAutorizacaoJwt(authenticationManager(), jwtComponent, userDetailsService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**");
        }
    }

    @Bean
    CorsConfigurationSource configuracaoDoCORS(){

        UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
        cors.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return cors;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

}
