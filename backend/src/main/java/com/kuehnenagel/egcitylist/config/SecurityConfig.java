package com.kuehnenagel.egcitylist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kuehnenagel.egcitylist.security.jwt.JwtEntryPoint;
import com.kuehnenagel.egcitylist.security.jwt.JwtTokenFilter;
import com.kuehnenagel.egcitylist.security.service.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Value("${spring.security.debug:false}")
	boolean securityDebug;
	//
	//	@Autowired
	//	UserDetailsServiceImpl userDetailsService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	//	@Autowired
	//	private JwtEntryPoint unauthorizedHandler;

	@Bean
	public AuthenticationEntryPoint unauthorizedHandler() {
		return new JwtEntryPoint();
	}


	@Bean
	public JwtTokenFilter authenticationJwtTokenFilter() {
		return new JwtTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Profile("!test")
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler()).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
		.anyRequest().authenticated();

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.debug(securityDebug)
				.ignoring()
				.antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
	}

	@Bean
	@Profile("test")
	public SecurityFilterChain filterChainDisabled(HttpSecurity http) throws Exception {

		http.authorizeRequests().anyRequest().permitAll();

		return http.build();
	}
}