package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup","/error", "/css/**", "/js/**","/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.csrf()
                .ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();

        http.formLogin()
                .loginPage("/login")
                .permitAll().defaultSuccessUrl("/home", true);;


        //http.logout().logoutSuccessUrl("/login?logout").permitAll();
        http.logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

}
