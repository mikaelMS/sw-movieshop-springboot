package com.oth.sw.mikesmovieshop.mikesmovieshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("labresources")
    private UserDetailsService userSecurityService;

    @Autowired
    private MySecurityUtilities securityUtilities;

    private BCryptPasswordEncoder passwordEncoder() {
        return securityUtilities.passwordEncoder();
    }

    private static final String[] ALLOW_ACCESS_WITHOUT_AUTHENTICATION = {
            "/css/**", "/images/**", "/js/**", "/fonts/**", "/cart", "/cart/add/**", "/cart/remove/**", "/products/**", "/", "/auth/login-error", "/auth/registration", "/h2/**"};

    private static final String[] ALLOW_ACCESS_ONLY_WITH_AUTHENTICATION = {
            "/auth/logout", "/cart/checkout"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(ALLOW_ACCESS_WITHOUT_AUTHENTICATION).permitAll()
                .antMatchers(ALLOW_ACCESS_ONLY_WITH_AUTHENTICATION).hasRole("USER");
        http
                .formLogin()
                    .loginPage("/auth/login").permitAll()
                    .defaultSuccessUrl("/cart", true)
                    .failureUrl("/auth/login-error")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                    .logoutSuccessUrl("/")
                    .deleteCookies("remember-me")
                    .permitAll()
                .and()
                    .sessionManagement()
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true);
//        http
//                .headers().frameOptions().disable();

        // needed for h2 login
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());
    }
}    