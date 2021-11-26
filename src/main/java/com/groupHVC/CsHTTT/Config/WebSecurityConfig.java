package com.groupHVC.CsHTTT.Config;

import com.groupHVC.CsHTTT.Service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Page for Admin
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

        //Page for User
        http.authorizeRequests().antMatchers("/profilePage", "/editProfile", "/cart").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");

        //Access denied exception
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        //Config login
        http.authorizeRequests()
                .antMatchers("/", "/register_page", "/products/**").permitAll() //Pages free access
                .and()
                .formLogin()
                .loginProcessingUrl("/login_check") //Submit URL
                .loginPage("/login_page")
                .defaultSuccessUrl("/")
                .failureUrl("/login_page?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/j_spring_security_logout");

        //Remember me
        http.authorizeRequests().and()
                .rememberMe().tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(1*24*60*60); //24h

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
