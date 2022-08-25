package com.ms.personApi.config.security;

import com.ms.personApi.repository.UserRepository;
import com.ms.personApi.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
    private AuthenticationService authenticationService;
    private TokenService tokenService;
    private UserRepository userRepository;
    //Metodo para injetar o authentication manager no controller.
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configurações de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/customer").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/customer/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/customer/search").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/customer/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/user").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/user").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/user/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/user").hasAnyAuthority("ADMIN","USER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/user").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
                .antMatchers("/h2-console/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationViaTokenFilter(tokenService,userRepository), UsernamePasswordAuthenticationFilter.class);

    }


    //Configurações de recursos estaticos(js,css,img)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    //Configurações de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
