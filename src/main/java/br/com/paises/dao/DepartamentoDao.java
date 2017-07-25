package br.com.paises.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.paises.entity.Departamento;
import br.com.paises.util.JPAUtil;

public class DepartamentoDao {
	
	public void adiciona(Departamento departamento) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.persist(departamento);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public void remove(Departamento departamento) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(departamento));
		manager.getTransaction().commit();
		manager.close();
	}

	public void atualiza(Departamento departamento) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		
		manager.merge(departamento);
		
		manager.getTransaction().commit();
		manager.close();
	}
	
	public List<Departamento> listaTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();
		CriteriaQuery<Departamento> query = manager.getCriteriaBuilder().createQuery(Departamento.class);
		query.select(query.from(Departamento.class));
		List<Departamento> lista = manager.createQuery(query).getResultList();
		manager.close();
		return lista; 
	}
}
