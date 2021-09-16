package br.com.empresa.lojaOrmMysql.dao;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.modelo.Cliente;

public class ClienteDAO {
	
	private EntityManager em;
	
	public ClienteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public Cliente buscarPorId(long id) {		
		return this.em.find(Cliente.class, id);
	}
}
