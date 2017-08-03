package br.com.paises.mb;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.paises.dao.DepartamentoDao;
import br.com.paises.entity.Departamento;

@Named
@SessionScoped
public class DepartamentoBean {
	
	private Departamento departamento = new Departamento();
	
	private List<Departamento> departamentos;
	
	@Inject
	private DepartamentoDao deptoDao;
	
	@PostConstruct
	public void init() {						
		try{
			departamentos = deptoDao.listaTodos();
		}catch(RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Departamentos!");
			erro.printStackTrace();
		}	
	}
	
	
	public void salvar() throws IOException {
				
		if (departamento.getId() == null){
				
			if (consultarDepartamentoPorCentroDeCusto(departamento.getCentroDeCusto())){
				Messages.addGlobalError("Este Centro de Custo já se encontra cadastrado.");
				return;
			}else{
				departamento.setDataCadastro(new Date());
				departamento.setDataAlteracao(null);
				deptoDao.adiciona(departamento);
			}
		}else{
			departamento.setDataAlteracao(new Date());
			deptoDao.atualiza(departamento);
		}
		
		this.departamento = new Departamento();
		this.departamentos = deptoDao.listaTodos();
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("listaDepartamentos.xhtml");
	}
	
	

	
	
	public void salvar2() throws IOException {
		String CC1 = "";
		String CC1Descricao = "";
		String CC2 = "";
		String CC2Descricao = "";
				
		CC2 = departamento.getCentroDeCusto(); 
		CC2 = CC2.replaceAll("[. ]", "");
		CC2Descricao = departamento.getDescricao().trim().toUpperCase();
				
		DepartamentoDao dao = new DepartamentoDao();
		Departamento depto = new Departamento();
		//depto = consultarPorCentroDeCusto(departamento.getCentroDeCusto());
		//List<Departamento> deptos = consultarDepartamentoPorCentroDeCusto(CC2);
		
		if (depto != null){
			CC1 = depto.getCentroDeCusto();
			CC1 = CC1.replaceAll("[. ]", "");
			CC1Descricao = depto.getDescricao().trim().toUpperCase();		
		}
		
		if (departamento.getId() == null){
				
			if (CC1.equals(CC2) && !CC1.isEmpty()){
				Messages.addGlobalError("Este Centro de Custo já se encontra cadastrado para: "+depto.getDescricao());
				depto=null;
				return;
			}else{
				departamento.setDataCadastro(new Date());
				departamento.setDataAlteracao(null);
				dao.adiciona(departamento);
			}	
		}else{
			if (CC1.equals(CC2) && !CC1Descricao.equals(CC2Descricao) && !CC1.isEmpty()){
				Messages.addGlobalError("Este Centro de Custo já se encontra cadastrado para: "+depto.getDescricao());
				depto=null;
				return;
			}else{
				departamento.setDataAlteracao(new Date());
				dao.atualiza(departamento);
			}	
		}
		
		this.departamento = new Departamento();
		this.departamentos = dao.listaTodos();
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("listaDepartamentos.xhtml");
	}

	
//	private String cpf;
//	
//	 public void validar() {
//	     if (cpf == null || "".equals(cpf)) {
//	         FacesContext.getCurrentInstance().addMessage("msgValidador", new FacesMessage("CPF Vazio"));
//	     } else if (cpf.length() != 11) {
//	         FacesContext.getCurrentInstance().addMessage("msgValidador", new FacesMessage("CPF deve ter 11 dígitos"));
//	     }else{
//	         FacesContext.getCurrentInstance().addMessage("msgValidador", new FacesMessage("CPF Validado com sucesso !!"));
//	     }
//	 }
//	
//	 public String getCpf() {
//	     return cpf;
//	 }
//	
//	 public void setCpf(String cpf) {
//	     this.cpf = cpf;
//	 }
	
	
	
	
	public void excluir(ActionEvent evento){
		departamento = (Departamento) evento.getComponent().getAttributes().get("departamentoSelecionado");
		deptoDao.remove(departamento);
		this.departamentos = deptoDao.listaTodos();
		
	}

	public void alterar(ActionEvent evento) throws IOException {
		departamento = (Departamento) evento.getComponent().getAttributes().get("departamentoSelecionado");
		
		if (departamento.getDataAlteracao() == null)
			departamento.setDataAlteracao(new Date());
		
		//return "departamento";
		FacesContext.getCurrentInstance().getExternalContext().redirect("departamento.xhtml");
	}
	
	public String novo() {
		departamento = new Departamento();
		departamento.setDataCadastro(new Date());
		return "departamento";
		//FacesContext.getCurrentInstance().getExternalContext().redirect("departamento.xhtml");
	}
	
	
	public Departamento buscaDepartamentoPorCentroDeCusto(String centroDeCusto) {
		Departamento depto = new Departamento();
		depto = deptoDao.buscaDepartamentoPorCentroDeCusto(this.departamento.getCentroDeCusto());
		
		return this.departamento;
	}

	
	private boolean consultarDepartamentoPorCentroDeCusto(String centroDeCusto){
		boolean verificaCentroDeCusto = deptoDao.consultarDepartamentoPorCentroDeCusto(centroDeCusto);
		
		return verificaCentroDeCusto;			
	}
	

	public void buscarPorId() {
		this.departamento = deptoDao.buscaPorId(this.departamento.getId()); 
	}
	
	
	
	
	
	
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
}