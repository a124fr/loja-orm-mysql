package br.com.empresa.lojaOrmMysql.testes;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.modelo.Categoria;
import br.com.empresa.lojaOrmMysql.util.JPAUtil;

public class CadastroDeCategoriaClicoDeVidaDeEntidadeJPA2 {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");	// ESTADO DO OBJETO É TRANSIENT	
		
		EntityManager em = JPAUtil.getEntityManager();	
		em.getTransaction().begin();
		
		
		em.persist(celulares); // ESTADO DO OBJETO É MANAGED
		celulares.setNome("XPTO");
		
		em.getTransaction().commit();
		em.close();
		
		celulares.setNome("1234"); // ESTADO DO OBJETO É DETACHED

	}

}
