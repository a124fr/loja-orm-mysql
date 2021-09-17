package br.com.empresa.lojaOrmMysql.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.modelo.Pedido;
import br.com.empresa.lojaOrmMysql.vo.RelatorioDeVendasVO;

public class PedidoDAO {
	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return this.em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<Object[]> relatoriodeVendas() {
		String jpql = "SELECT produto.nome, SUM(item.quantidade), MAX(pedido.data) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto GROUP BY produto.nome "
				+ "ORDER BY SUM(item.quantidade) DESC";
		return this.em.createQuery(jpql, Object[].class)
				.getResultList();
	}
	
	public List<RelatorioDeVendasVO> relatoriodeVendas2() {
		String jpql = "SELECT new br.com.empresa.lojaOrmMysql.vo.RelatorioDeVendasVO ("
				+ "produto.nome, "
				+ "SUM(item.quantidade), MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto GROUP BY produto.nome "
				+ "ORDER BY SUM(item.quantidade) DESC";
		return this.em.createQuery(jpql, RelatorioDeVendasVO.class)
				.getResultList();
	}
}
