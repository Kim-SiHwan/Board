package jpaboard.jpaboard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/boards/home").permitAll()
                .antMatchers("/boards/view").permitAll()
                .antMatchers("/boards/uploadBoard").hasAnyRole("ADMIN","USER")
                .antMatchers("/h2/*").permitAll()
                .antMatchers("/replies/**").permitAll();
        http.csrf()
                .ignoringAntMatchers("/replies/**")
                .ignoringAntMatchers("/h2/**")
                .ignoringAntMatchers("/boards/uploadBoard")
                .ignoringAntMatchers("/like/**");
        http.headers().frameOptions().disable();
        http.formLogin().defaultSuccessUrl("/boards/home");
        http.logout().logoutSuccessUrl("/boards/home");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
