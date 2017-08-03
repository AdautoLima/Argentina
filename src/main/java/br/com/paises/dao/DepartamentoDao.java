package br.com.paises.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.paises.entity.Departamento;

public class DepartamentoDao {
	
	@Inject
	private EntityManager manager;
	
	public void adiciona(Departamento departamento) {
		manager.getTransaction().begin();
		manager.persist(departamento);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public void remove(Departamento departamento) {
		manager.getTransaction().begin();
		manager.remove(manager.merge(departamento));
		manager.getTransaction().commit();
		manager.close();
	}

	public void atualiza(Departamento departamento) {
		manager.getTransaction().begin();
		
		manager.merge(departamento);
		
		manager.getTransaction().commit();
		manager.close();
	}
		
	public List<Departamento> listaTodos() {
		CriteriaQuery<Departamento> query = manager.getCriteriaBuilder().createQuery(Departamento.class);
		query.select(query.from(Departamento.class));
		List<Departamento> lista = manager.createQuery(query).getResultList();
		manager.close();
		return lista; 
	}
	
	
	public Departamento buscaPorId(Integer id) {
		return manager.find(Departamento.class, id);
	}
	
	
	public List<Departamento> consultarTodos() {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Departamento> c = cb.createQuery(Departamento.class);  
		TypedQuery<Departamento> q = manager.createQuery(c);
		// limitando a qtde. de departamentos na resposata da consulta. 
		q.setMaxResults(12);	
		return q.getResultList();
	}
	
	
	public Departamento buscaDepartamentoPorCentroDeCusto(String centroDeCusto) {
		return manager
	        .createNamedQuery("Departamento.buscaDepartamentoPorCentroDeCusto", Departamento.class)
	        .setParameter("centroDeCusto", centroDeCusto)
	        .getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public boolean consultarDepartamentoPorCentroDeCusto(String centroDeCusto) {
		Query query = manager.createNamedQuery("Departamento.consultarDepartamentoPorCentroDeCusto");
		query.setParameter("centroDeCusto", centroDeCusto);
		
		List<Departamento> deptos = query.getResultList(); 
		if (deptos.isEmpty())
			return false;
		else
			return true;
	}
	
   
	
}
