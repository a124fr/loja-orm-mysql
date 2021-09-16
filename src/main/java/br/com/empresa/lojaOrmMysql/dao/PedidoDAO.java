package br.com.empresa.lojaOrmMysql.dao;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.modelo.Pedido;

public class PedidoDAO {
	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
}
