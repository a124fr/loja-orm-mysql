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

public class PerfomanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDAO pedidoDAO = new PedidoDAO(em);
//		Pedido pedido = em.find(Pedido.class, 1l);
		
		Pedido pedido = pedidoDAO.buscarPedidoComCliente(1l); // Testando Query Planejada usando JOIN FETCH.		
		em.close(); 
		
//		para caso onde não tenhos o controle do EntityManager. O carremento Lazy pode não funcionar. 
//		Então é usando uery Planejada com JOIN FETCH.
		
//		System.out.println(pedido.getData());
//		System.out.println(pedido.getItens().size());
		System.out.println(pedido.getCliente().getNome());
		
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("1000"), videogames);
		Produto mackbook = new Produto("MacBook", "Macbook pro retina", new BigDecimal("5000"), informatica);
		
		Cliente cliente = new Cliente("Rodrigo", "123456");
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido2, mackbook));
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		PedidoDAO pedidoDAO = new PedidoDAO(em);			
		

		em.getTransaction().begin();

		categoriaDAO.cadastrar(celulares);
		categoriaDAO.cadastrar(videogames);
		categoriaDAO.cadastrar(informatica);
		
		produtoDAO.cadastrar(celular);
		produtoDAO.cadastrar(videogame);
		produtoDAO.cadastrar(mackbook);
		
		clienteDAO.cadastrar(cliente);
		
		pedidoDAO.cadastrar(pedido);
		pedidoDAO.cadastrar(pedido2);
		
		em.getTransaction().commit();
		em.close();
	}

}
