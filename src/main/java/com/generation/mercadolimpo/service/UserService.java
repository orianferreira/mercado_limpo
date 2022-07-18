package com.generation.mercadolimpo.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.generation.mercadolimpo.model.Usuario;
import com.generation.mercadolimpo.model.UsuarioLogin;
import com.generation.mercadolimpo.repository.UsuarioRepository;

@Service
public class UserService {

	@Autowired
	private UsuarioRepository repository;
	
	
	public Usuario register(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String passwordEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(passwordEncoder);
		
		return repository.save(usuario);
	}
	
	
	public Optional<UsuarioLogin> login(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				user.get().setToken(authHeader);
				user.get().setEmail(usuario.get().getEmail());
				
				return user;
			}
		}
		
		return null;
	}
	
	
	public Optional<Usuario> update(Usuario usuario) {
		
		if(repository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = repository.findByEmail(usuario.getEmail());
				
			if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId())) {
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}		
			usuario.setSenha(encryptPassword(usuario.getSenha()));
			
			return Optional.ofNullable(repository.save(usuario));	
		}
		
		return Optional.empty();
	}
	
	
	private String encryptPassword(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();	
		return encoder.encode(senha);

	}
}
