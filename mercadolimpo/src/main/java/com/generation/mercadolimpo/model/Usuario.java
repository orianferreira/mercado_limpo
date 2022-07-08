package com.generation.mercadolimpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

@Table(name = "tb_usuario")

public class Usuario {

	@Id

	// @GeneratedValue fara o auto_increment no id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// @NotBlank -> Válida se o campo está nulo ou vazio.
	@NotBlank(message = "Nome não pode estar vazio ou nulo.")
	@Size(min = 10, max = 100)
	private String nome;

	@NotBlank(message = "Email não pode estar vazio ou nulo.")	
	@Email(message = "Email invalido!")
	@Size(min = 10, max = 100)
	private String email;

	@NotBlank(message = "Email não pode estar vazio ou nulo.")
	@Pattern(regexp = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z", message = "1 caracter maiusculo \n 1 caracter especial")
	private String senha;

	private String foto;

	private boolean admin = false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
