package com.generation.mercadolimpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.mercadolimpo.model.Usuario;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {
	public List<Usuario>findAllByNomeContainingIgnoreCase(String nome);

	public List<Usuario> findAllByEmailContainingIgnoreCase(String email);
	
	

}
