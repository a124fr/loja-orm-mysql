package br.com.empresa.lojaOrmMysql.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.dao.CategoriaDAO;
import br.com.empresa.lojaOrmMysql.dao.ProdutoDAO;
import br.com.empresa.lojaOrmMysql.modelo.Categoria;
import br.com.empresa.lojaOrmMysql.modelo.Produto;
import br.com.empresa.lojaOrmMysql.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
//		Produto celular = new Produto();		
//		celular.setNome("Xiaomi Redmi");
//		celular.setDescricao("muito legal");
//		celular.setPreco(new BigDecimal("800"));
//		
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja-orm-mysql");
//		
//		EntityManager em  = factory.createEntityManager();
//		em.getTransaction().begin();
//		em.persist(celular);
//		em.getTransaction().commit();
//		em.close();
		
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
