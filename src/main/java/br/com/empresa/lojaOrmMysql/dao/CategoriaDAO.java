package br.com.empresa.lojaOrmMysql.dao;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.modelo.Categoria;

public class CategoriaDAO {
	
	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
}
