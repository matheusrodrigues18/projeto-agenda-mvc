package br.com.cotiinformatica.entities;

import java.util.Date;

public class Schedule {
	
	private Integer idTarefa;
	private String nome;
	private String descricao;
	private Date data;
	private String hora;
	private String prioridade;
	
	private User usuario;

	public Schedule() {
		super();
	}

	public Schedule(Integer idTarefa, String nome, String descricao, Date data, String hora, String prioridade,
			User usuario) {
		super();
		this.idTarefa = idTarefa;
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
		this.prioridade = prioridade;
		this.usuario = usuario;
	}

	public Integer getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(Integer idTarefa) {
		this.idTarefa = idTarefa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Tarefa [idTarefa=" + idTarefa + ", nome=" + nome + ", descricao=" + descricao + 
				", data=" + data + ", hora=" + hora + ", prioridade=" + prioridade + 
				", usuario=" + usuario + "]";
	}
	
	
}
