package br.com.empresa.lojaOrmMysql.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	public List<Produto> buscarPorNome(String nome) {		
		return em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class)
				.setParameter("nome", nome) // Named parameters
				.getResultList();
	}
	
	public List<Produto> buscarPorNome2(String nome) {		
		return em.createQuery("SELECT p FROM Produto p WHERE p.nome = ?1", Produto.class)
				.setParameter(1, nome) // parametro posicional
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome) {		
		return em.createQuery("SELECT p FROM Produto p WHERE p.categoria.nome = :nome", Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria2(String nome) {		
		return em.createNamedQuery("Produto.produtosPorCategoria",Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {		
		return em.createQuery("SELECT p.preco FROM Produto p WHERE p.nome = :nome", BigDecimal.class)
				.setParameter("nome", nome) 
				.getSingleResult(); // carrega um único resultado de uma consulta
	}
	
	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";				
			
			if (nome != null && !nome.trim().equals("")) {
				jpql = " AND p.nome = :nome";
			}
			
			if(preco != null) {
				jpql = " AND p.preco = :preco";
			}
			
			if (dataCadastro != null) {
				jpql = " AND p.dataCadastro = :dataCadastro";
			}
			
			TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
			
			if (nome != null && !nome.trim().equals("")) {
				query.setParameter("nome", nome);
			}
			
			if(preco != null) {
				query.setParameter("preoc", preco);
			}
			
			if (dataCadastro != null) {
				query.setParameter("dataCadastro", dataCadastro);
			}
			
			return query.getResultList();
	}
}
