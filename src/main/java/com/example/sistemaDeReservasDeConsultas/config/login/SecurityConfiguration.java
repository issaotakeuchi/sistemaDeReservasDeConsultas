package com.example.sistemaDeReservasDeConsultas.config.login;

import com.example.sistemaDeReservasDeConsultas.enums.login.UsuarioRoles;
import com.example.sistemaDeReservasDeConsultas.service.login.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService service;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers("/consultas/**").hasAnyAuthority(UsuarioRoles.ROLE_ADMIN.name(), UsuarioRoles.ROLE_USER.name())
                .antMatchers("/dentistas/**", "/pacientes/**").hasAnyAuthority(UsuarioRoles.ROLE_ADMIN.name())
                .anyRequest().authenticated().and().formLogin().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder.encoder());
        provider.setUserDetailsService(service);
        return provider;
    }
}
