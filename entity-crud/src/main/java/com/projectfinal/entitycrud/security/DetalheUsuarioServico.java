package com.projectfinal.entitycrud.security;


import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projectfinal.entitycrud.modelo.Papel;
import com.projectfinal.entitycrud.modelo.Usuario;
import com.projectfinal.entitycrud.repositorio.UsuarioRepository;



@Service
@Transactional
public class DetalheUsuarioServico implements UserDetailsService {

	private UsuarioRepository usuarioRepository;
	
	public DetalheUsuarioServico(UsuarioRepository usuarioRepository) {							//construtor para injetar a dependencia no usuariorepository
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByLogin(username);
		
		if(usuario != null && usuario.isAtivo()) {												//verifica a existencia do usuario e se o mesmo esta ativo
			Set<GrantedAuthority> papeisDoUsuario = new HashSet<GrantedAuthority>();
			for(Papel papel: usuario.getPapeis()) {												//percorendo os papeis um por um e criandoum objeto que vai armazenar as strings no caso os p
				GrantedAuthority pp = new SimpleGrantedAuthority(papel.getPapel());
				papeisDoUsuario.add(pp);														
			}			
			User user = new User(usuario.getLogin(), usuario.getPassword(), papeisDoUsuario);
			return user;
		} else {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
	}

}

