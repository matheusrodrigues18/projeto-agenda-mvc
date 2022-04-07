package br.com.cotiinformatica.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private Integer idUsuario;
	private String nome;
	private String email;
	private String senha;
	
	private List<Schedule> listaTarefa = new ArrayList<Schedule>();
	
	public User() {
		super();
	}

	public User(Integer idUsuario, String nome, String email, String senha, List<Schedule> listaTarefa) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.listaTarefa = listaTarefa;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public List<Schedule> getListaTarefa() {
		return listaTarefa;
	}

	public void setListaTarefa(List<Schedule> listaTarefa) {
		this.listaTarefa = listaTarefa;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + ", email=" + email 
				+ ", senha=" + senha + "]";
	}
	
}
