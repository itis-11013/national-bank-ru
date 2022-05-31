package ru.itis.nationalbankru.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.itis.nationalbankru.security.details.UserDetailsServiceImpl;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final DataSource dataSource;

    @Qualifier("customUserDetailsService")
    private final UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource, UserDetailsServiceImpl userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/auth/*").permitAll();
        //                .antMatchers("/").authenticated()
        //                .antMatchers("/product/*").authenticated()
        //                .antMatchers("/payment/*").authenticated()
        //                .antMatchers("/contract/*").authenticated()
        //                .antMatchers("/organization/*").authenticated()
        //                .antMatchers("/user/*").authenticated()
        //                .antMatchers("/admin/*").hasAuthority("ADMIN");

        http
                .formLogin()
                .loginPage("/auth/signIn")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/auth/signIn?error");

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
