package KalongKingdom.corp.clientapp.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**")
                .permitAll()
                .antMatchers("/login/**", "/register/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successForwardUrl("/home")
                .failureForwardUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();

        // http
        // .authorizeRequests(requests ->
        // requests
        // .antMatchers("/css/**", "/js/**", "/images/**")
        // .permitAll()
        // .antMatchers("/login/**")
        // .permitAll()
        // // .antMatchers("/signup/**")
        // // .permitAll()
        // .antMatchers(HttpMethod.POST, "/registration")
        // .permitAll()
        // .anyRequest()
        // .authenticated()
        // )
        // .formLogin(login ->
        // login
        // .loginPage("/login")
        // .loginProcessingUrl("/login")
        // .successForwardUrl("/home")
        // .failureForwardUrl("/login?error=true")
        // .permitAll()
        // )
        // .logout(
        // logout -> logout.logoutUrl("/logout")
        // .permitAll());

    }
}
