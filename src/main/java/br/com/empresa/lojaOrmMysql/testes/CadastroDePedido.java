package br.com.empresa.lojaOrmMysql.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.dao.CategoriaDAO;
import br.com.empresa.lojaOrmMysql.dao.ClienteDAO;
import br.com.empresa.lojaOrmMysql.dao.PedidoDAO;
import br.com.empresa.lojaOrmMysql.dao.ProdutoDAO;
import br.com.empresa.lojaOrmMysql.modelo.Categoria;
import br.com.empresa.lojaOrmMysql.modelo.Cliente;
import br.com.empresa.lojaOrmMysql.modelo.ItemPedido;
import br.com.empresa.lojaOrmMysql.modelo.Pedido;
import br.com.empresa.lojaOrmMysql.modelo.Produto;
import br.com.empresa.lojaOrmMysql.util.JPAUtil;

public class CadastroDePedido {
	public static void main(String[] args) {
		popularBancoDeDados();

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		Produto produto = produtoDAO.buscarPorId(1l);
		Cliente cliente = clienteDAO.buscarPorId(1l);
				
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));

		em.getTransaction().begin();
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
				
		pedidoDAO.cadastrar(pedido);

		em.getTransaction().commit();
		em.close();
	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "muito legal", new BigDecimal("800"), celulares);
		
		Cliente cliente = new Cliente("Rodrigo", "123456");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);

		em.getTransaction().begin();

		categoriaDAO.cadastrar(celulares);
		produtoDAO.cadastrar(celular);
		clienteDAO.cadastrar(cliente);

		em.getTransaction().commit();
		em.close();
	}
}
