package ru.netology.hibernate.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}adminAdmin").authorities("read", "write")
                .and()
                .withUser("person").password("{noop}person").authorities("read");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("persons/create-new-person").permitAll()
                .and()
                .authorizeRequests().antMatchers("persons/create").permitAll()
                .and()
                .authorizeRequests().antMatchers("persons/all-users").hasAuthority("read")
                .and()
                .authorizeRequests().antMatchers("persons/update-phone").hasAuthority("write")
                .and()
                .authorizeRequests().antMatchers("persons/update-city").hasAuthority("write")
                .and()
                .authorizeRequests().antMatchers("persons/delete").hasAuthority("write")
                .and()
                .authorizeRequests().antMatchers("persons/by-city").hasAuthority("read")
                .and()
                .authorizeRequests().antMatchers("persons/by-age").hasAuthority("read")
                .and()
                .authorizeRequests().antMatchers("persons/by-name-surname").hasAuthority("read")
                .and()
                .authorizeRequests().anyRequest().authenticated().and().csrf().disable();
    }
}
