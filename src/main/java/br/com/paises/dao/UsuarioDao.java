package br.com.paises.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.paises.entity.Usuario;

public class UsuarioDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public void adiciona(Usuario usuario) {
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		manager.close();
	}


	public void remove(Usuario usuario) {
		manager.getTransaction().begin();
		manager.remove(manager.merge(usuario));
		manager.getTransaction().commit();
		manager.close();
	}

	public void atualiza(Usuario usuario) {
		manager.getTransaction().begin();
		manager.merge(usuario);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<Usuario> buscaPorNome(String nome) {
		String jpql = "select u from Usuario u where "
				+ " lower(u.nome) like :nome order by u.nome";

		List<Usuario> lista = manager.createQuery(jpql, Usuario.class)
				.setParameter("nome", nome + "%").getResultList();
		manager.close();
		return lista; 
	}

	public List<Usuario> listaTodos() {
		CriteriaQuery<Usuario> query = manager.getCriteriaBuilder().createQuery(Usuario.class);
		query.select(query.from(Usuario.class));
		List<Usuario> lista = manager.createQuery(query).getResultList();
		manager.close();
		return lista; 
	}
	
	public Usuario buscaPorId(Long id) {
		Usuario usuario = manager.find(Usuario.class, id);
		manager.close();
		return usuario;
	}
}
