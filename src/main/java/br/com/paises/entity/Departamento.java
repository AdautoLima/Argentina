package br.com.paises.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Departamento {
	
	/*
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="DEPARTAMENTO_SEQUENCIA")
	@SequenceGenerator(name="DEPARTAMENTO_SEQUENCIA",sequenceName="DEPARTAMENTO_SEQUENCIA",allocationSize=0)
	*/
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEPTO_CODIGO",nullable=false)
	private Integer id;
	
	@Column(name="DESCRICAO",nullable=false)	
	private String descricao;	
		
	@Column(name="DEPTO_CENTRO_CUSTO",nullable=false)	
	private String centroDeCusto;	
		
	@Column(name="DEPTO_STATUS",nullable=false)
	private String status;			 
		
	@Column(name="DEPTO_DATA_CADASTRO",nullable=false)
	private Calendar dataCadastro;	 
	
	//@Temporal(TemporalType.DATE)
	//private Calendar dataNascimento = Calendar.getInstance();
		
	@Column(name="DEPTO_DATA_ALTERACAO")
	private Calendar dataAlteracao;
	
	public Departamento() {
		super();
	}

	

	public Departamento(Integer id, String descricao, String centroDeCusto, String status, Calendar dataCadastro,
			Calendar dataAlteracao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.centroDeCusto = centroDeCusto;
		this.status = status;
		this.dataCadastro = dataCadastro;
		this.dataAlteracao = dataAlteracao;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCentroDeCusto() {
		return centroDeCusto;
	}

	public void setCentroDeCusto(String centroDeCusto) {
		this.centroDeCusto = centroDeCusto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	
	
}
