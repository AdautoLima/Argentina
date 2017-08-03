package br.com.paises.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@NamedQueries({
	@NamedQuery(name="Departamento.buscaDepartamentoPorCentroDeCusto", 
				query="select d from Departamento d where d.centroDeCusto = :centroDeCusto"),

	@NamedQuery(name = "Departamento.consultarDepartamentoPorCentroDeCusto",
				query = " SELECT d FROM Departamento d WHERE d.centroDeCusto = :centroDeCusto")
})

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
	private Character status;			 
		
	@Column(name="DEPTO_DATA_CADASTRO",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;	 
	
	@Column(name="DEPTO_DATA_ALTERACAO")
	@Temporal(TemporalType.DATE)
	private Date dataAlteracao;
	
	public Departamento() {
		super();
	}

	@Transient  
	public String getStatusFormatado() {
		String statusFormatado = null;
		
		switch (status) {
			case 'A': statusFormatado = "Ativo";
			break;
			case 'I': statusFormatado = "Inativo";
			break;
		}
		return statusFormatado;
	}
	
	
	
	
	

	public Departamento(Integer id, String descricao, String centroDeCusto, Character status, Date dataCadastro,
			Date dataAlteracao) {
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

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	
	
}
