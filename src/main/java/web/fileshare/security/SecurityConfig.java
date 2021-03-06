package web.fileshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.fileshare.service.impl.LoginSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginSecurityService loginSecurity;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/", "/resources/**").permitAll()
                    .antMatchers("/service/delete").hasRole("ADMIN") // ROLE_
                    .anyRequest().authenticated()
                .and().formLogin()
                    .loginPage("/").loginProcessingUrl("/login").failureUrl("/")
                    .defaultSuccessUrl("/service", true)
                    .usernameParameter("id")
                    .passwordParameter("password")
                .and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .clearAuthentication(true)
                    .permitAll().logoutSuccessUrl("/")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and().csrf().disable();
        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginSecurity);
    }
}
