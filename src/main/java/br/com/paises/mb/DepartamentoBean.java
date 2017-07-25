package br.com.paises.mb;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.paises.dao.DepartamentoDao;
import br.com.paises.entity.Departamento;

@ManagedBean
@ViewScoped
public class DepartamentoBean {
	private Departamento departamento = new Departamento();
	private List<Departamento> departamentos;
	
	
	@PostConstruct
	public void init() {	
		//Calendar calendar = Calendar.getInstance();
		
	}
	
	
	public Departamento getDepartamento() {
		return this.departamento;
	}
		
	public void gravar() {
		Calendar calendar = Calendar.getInstance();
		
		DepartamentoDao dao = new DepartamentoDao();
			
		if(departamento.getId() == null){			
			departamento.setDataCadastro(calendar);			
			departamento.setDataAlteracao(null);
			
			dao.adiciona(departamento);
		} else {
			
			departamento.setDataAlteracao(calendar);
			dao.atualiza(departamento);
		}
		
		this.departamento = new Departamento();
		//this.departamentos = dao.listaTodos();		
	}

	public List<Departamento> getDepartamentos() {
		if(departamentos == null){
			System.out.println("Carregando Departamento...");
			departamentos = new DepartamentoDao().listaTodos();
		}
		return departamentos;
	}
	
	public void remover(Departamento departamento){
		DepartamentoDao dao = new DepartamentoDao();
		dao.remove(departamento);
		this.departamentos = dao.listaTodos();
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
}