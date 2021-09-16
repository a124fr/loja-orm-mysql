package br.com.empresa.lojaOrmMysql.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.modelo.Produto;

public class ProdutoDAO {
	
	private EntityManager em;
	
	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}

	public Produto buscarPorId(long id) {		
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {		
		return em.createQuery("SELECT p FROM Produto p", Produto.class)
				.getResultList();
	}
}
