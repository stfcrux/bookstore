package com.example.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// only allow admin user to access the delete endpoint
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	//Allow access to H2 console for testing
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/api/books/delete").hasRole("ADMIN")
            .antMatchers("/").hasRole("USER")
            .and().formLogin();

        // Settings to Enable easy H2 console and postman access for testing
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();
    }
	
	//Hard code USER and Admin Account for Proof of concept test
	@Override
	public void configure(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("USER").password("abcd1234").roles("USER")
			.and()
			.withUser("ADMIN").password("abcd1234").roles("USER","ADMIN");
	}
	

	
	
}
