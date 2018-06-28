package it.dstech.fantacalcio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/user/login", "/user/register", "/user/deleteOne/**", "/user/findOne/**" ,"/user/findAll", "/user/getUserModel", "/user/update", "/user//findByUsername/**").permitAll()
		.antMatchers("/campionato/findOne/**" , "/campionato/findAll" , "/squadra/findOne/**" , "/squadra/findAll" , "/partita/risultatoPartita").permitAll()
		.antMatchers( "/user/deleteAll", "/campionato/create" , "/campionato/update/**" , "/campionato/deleteOne/**" , "/campionato/deleteAll" , "/squadra/deleteAll" , "/partita/scontroSettimanale").hasAnyRole("ADMIN")
		.antMatchers( "/giocatore/createOne" , "/giocatore/createLista" , "/giocatore/deleteOne/**" , "/giocatore/deleteAll" , "/giocatore/findOne/**" , "/giocatore/findAll" , "/giocatore/update" , "/giocatore/aggiornaPunteggioGiocatore" ).hasAnyRole("ADMIN")
		.antMatchers("/squadra/create" , "/squadra/sceltaFormazione" , "/squadra/deleteOne" , "/squadra/update" , "/giocatore/compraGiocatore").hasAnyRole("USER")
		.antMatchers(HttpMethod.GET, "/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/**").permitAll()
		.antMatchers(HttpMethod.POST, "/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.anyRequest().authenticated().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.permitAll().and().csrf().disable();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");

		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
