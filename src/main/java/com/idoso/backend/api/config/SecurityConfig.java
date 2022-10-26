package com.idoso.backend.api.config;

import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.AuthenticationService;
import com.idoso.backend.api.domain.service.TokenService;
import com.idoso.backend.api.filter.AutenticacaoViaTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationService authService;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(POST, "/auth").permitAll()
                .antMatchers("/open/**").permitAll()
                .antMatchers("/h2-console/", "/h2-console/**").permitAll()
                .antMatchers("/prestador/**").permitAll()
                .antMatchers("/anuncios/**").permitAll()
                .antMatchers("/usuarios/**").permitAll()
                .antMatchers("/idoso/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().sameOrigin();
        http.cors().configurationSource(req -> new CorsConfiguration().applyPermitDefaultValues());
    }


}
