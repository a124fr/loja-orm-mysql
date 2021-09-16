package br.com.empresa.lojaOrmMysql.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.dao.CategoriaDAO;
import br.com.empresa.lojaOrmMysql.dao.ProdutoDAO;
import br.com.empresa.lojaOrmMysql.modelo.Categoria;
import br.com.empresa.lojaOrmMysql.modelo.Produto;
import br.com.empresa.lojaOrmMysql.util.JPAUtil;

public class CadastroDeProduto2 {

	public static void main(String[] args) {		
		cadastrarProduto();
		
		EntityManager em  = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		Produto produto = produtoDAO.buscarPorId(1l);
		System.out.println(produto.getPreco());
		
		List<Produto> produtos = produtoDAO.buscarTodos();
		produtos.forEach(p -> System.out.println(p.getNome()));
		
		em.close();
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "muito legal", new BigDecimal("800"), celulares);
		
		EntityManager em  = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		categoriaDAO.cadastrar(celulares);
		produtoDAO.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}

}
