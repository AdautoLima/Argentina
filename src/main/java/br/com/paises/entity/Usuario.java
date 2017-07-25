package br.com.paises.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/*
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USUARIO_SEQUENCIA")
	@SequenceGenerator(name="USUARIO_SEQUENCIA",sequenceName="USUARIO_SEQUENCIA",allocationSize=0)
	*/
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USU_CODIGO",nullable=false)
	private Integer id;
	
	@Column(name="USU_LOGIN",nullable=false)	
	private String login;	
	
	@Column(name="USU_SENHA",nullable=false)
	private String senha;	 
	   
	@Column(name="USU_NOME",nullable=false)
	private String nome;	 
		 
	@Column(name="USU_STATUS",nullable=false)
	private String status;	 
		 
	@ManyToOne
	@JoinColumn(nullable=false)
	//@Column(name="USU_DEPARTAMENTO",nullable=false)
	private Departamento departamento;	 
		
	@Column(name="USU_CADASTRO",nullable=false)
	private Calendar dataCadastro;	 
	
	@Column(name="USU_ALTERACAO")
	private Calendar dataAlteracao;
		
	public Usuario() {
		super();
	}


	public Usuario(Integer id, String login, String senha, String nome, String status, Departamento departamento,
			Calendar dataCadastro, Calendar dataAlteracao) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.status = status;
		this.departamento = departamento;
		this.dataCadastro = dataCadastro;
		this.dataAlteracao = dataAlteracao;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		
}

