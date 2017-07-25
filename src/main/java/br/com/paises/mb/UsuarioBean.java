package br.com.paises.mb;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.paises.dao.DepartamentoDao;
import br.com.paises.dao.UsuarioDao;
import br.com.paises.entity.Departamento;
import br.com.paises.entity.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	
	private List<Usuario> usuarios;
	private List<Departamento> departamentos;
	
	
	@PostConstruct
	public void init() {
		DepartamentoDao departamentoDao = new DepartamentoDao();
		departamentos = departamentoDao.listaTodos();
	}
	
	
	public void gravar() {
		System.out.println("Chamou");
		
		UsuarioDao dao = new UsuarioDao();
		
		Calendar calendar = Calendar.getInstance();
		
		if(usuario.getId() == null){
			usuario.setDataCadastro(calendar);			
			usuario.setDataAlteracao(null);
			dao.adiciona(usuario);
		} else {
			usuario.setDataAlteracao(calendar);
			dao.atualiza(usuario);
		}
		
		this.usuario = new Usuario();
		//this.usuarios = dao.listaTodos();		
	}

	public List<Usuario> getUsuarios() {
		
		if(usuarios == null){
			System.out.println("Carregando Usuários...");
			usuarios = new UsuarioDao().listaTodos();
		}
		
		return usuarios;
	}
	
	public void remover(Usuario usuario){
		UsuarioDao dao = new UsuarioDao();
		dao.remove(usuario);
		this.usuarios = dao.listaTodos();
	}
	
	
	public Usuario getUsuario() {
		return this.usuario;
	}
		
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	
}