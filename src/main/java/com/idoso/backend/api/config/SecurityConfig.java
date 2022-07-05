package com.idoso.backend.api.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.AuthenticationServiceImpl;
import com.idoso.backend.api.domain.service.TokenServiceImpl;
import com.idoso.backend.api.filter.AutenticacaoViaTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationServiceImpl authService;
    private final TokenServiceImpl tokenServiceImpl;
    private final UsuarioRepository usuarioRepository;

    public SecurityConfig(
            AuthenticationServiceImpl authService,
            TokenServiceImpl tokenServiceImpl,
            UsuarioRepository usuarioRepository) {
        this.authService = authService;
        this.tokenServiceImpl = tokenServiceImpl;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(POST, "/auth").permitAll()
            .antMatchers("/h2-console/", "/h2-console/**").permitAll()
            .antMatchers(GET, "/users").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(new AutenticacaoViaTokenFilter(tokenServiceImpl, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
