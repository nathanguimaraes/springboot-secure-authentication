package com.projectfinal.entitycrud.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.projectfinal.entitycrud.repositorio.UsuarioRepository;




@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private LoginSucesso loginSucesso;
	
	@Bean
	public BCryptPasswordEncoder gerarCriptografia() {
		BCryptPasswordEncoder criptografia = new BCryptPasswordEncoder();			//criando objt de criptografia 
		return criptografia;
	}
	
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		DetalheUsuarioServico detalheDoUsuario = new DetalheUsuarioServico(usuarioRepository);
		return detalheDoUsuario;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()																					//lambda
                .antMatchers("/").permitAll()
                .antMatchers("/auth/user/*").hasAnyAuthority("USER", "ADMIN", "BIBLIOTECARIO")
                .antMatchers("/auth/admin/*").hasAnyAuthority("ADMIN")
                .antMatchers("/auth/biblio/*").hasAnyAuthority("BIBLIOTECARIO")
                .antMatchers("/usuario/admin/*").hasAnyAuthority("ADMIN")
                .and()
                .exceptionHandling(handling -> handling.accessDeniedPage("/auth/auth-acesso-negado"))
                .formLogin(login -> login.successHandler(loginSucesso)
                        .loginPage("/login").permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))					//exibindo tela inicial apos sair
                        .logoutSuccessUrl("/").permitAll());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// O objeto que vai obter os detalhes do usuário
		UserDetailsService detalheDoUsuario = userDetailsServiceBean();
		// Objeto para criptografia
		BCryptPasswordEncoder criptografia = gerarCriptografia();
		
		auth.userDetailsService(detalheDoUsuario).passwordEncoder(criptografia);
	}
}

