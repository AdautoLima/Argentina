package br.com.paises.mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

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
	
	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private DepartamentoDao deptoDao;
	
	@PostConstruct
	public void init() {
		try{
			departamentos = deptoDao.listaTodos();
			usuarios = usuarioDao.listaTodos();
		}catch(RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Usuários!");
			erro.printStackTrace();
		}		
	}
	
	
	// para sair da aplicação
	// geralmente volta para a página de login
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "session?faces-redirect=true";
	}
		
	public void gravar() {
					
		if(usuario.getId() == null){
			usuario.setDataCadastro(new Date());			
			usuario.setDataAlteracao(null);
			usuarioDao.adiciona(usuario);
		} else {
			usuario.setDataAlteracao(new Date());
			usuarioDao.atualiza(usuario);
		}
		
		this.usuario = new Usuario();
		this.usuarios = usuarioDao.listaTodos();		
	}

	
	public void excluir(Usuario usuario){
		usuarioDao.remove(usuario);
		this.usuarios = usuarioDao.listaTodos();
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public List<Departamento> getDepartamentos() {
		return departamentos;
	}


	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

	
	
	
	
}