package com.moose.expensemanager.config;

import com.moose.expensemanager.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public CustomSecurityConfig (CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http    /* Config spring security so everyone can access main home, login, registration page line => 28-31 */
                    .authorizeRequests()
                    .antMatchers("/", "/login", "/register")
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
                    .csrf().disable()
                    .formLogin().loginPage("/login")
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/expenses")
                    .usernameParameter("email") // reference username to field in "/login"
                    .passwordParameter("password") // reference password to field in "/login"
                .and()
                    .logout().invalidateHttpSession(true).clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();


    }

    // ignore the resources, js/css files
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/resources/**", "/js/**", "/css/**", "/css/images/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    // configure and implement our login authentication logic, CustomUserDetailsService
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService); // reference to our custom UserDetails method authentication
    }
}
