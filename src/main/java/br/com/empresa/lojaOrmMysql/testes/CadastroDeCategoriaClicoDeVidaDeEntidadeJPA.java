package br.com.empresa.lojaOrmMysql.testes;

import javax.persistence.EntityManager;

import br.com.empresa.lojaOrmMysql.modelo.Categoria;
import br.com.empresa.lojaOrmMysql.util.JPAUtil;

public class CadastroDeCategoriaClicoDeVidaDeEntidadeJPA {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");	// ESTADO DO OBJETO É TRANSIENT	
		
		EntityManager em = JPAUtil.getEntityManager();	
		em.getTransaction().begin();
		
		
		em.persist(celulares); // ESTADO DO OBJETO É MANAGED
		celulares.setNome("XPTO");
		
		em.flush();
		em.clear();
		
		// ESTADO DO OBJETO É DETACHED -> objeto celulares
		
		celulares = em.merge(celulares); // ESTADO DO OBJETO É MANAGED
		celulares.setNome("1234"); 
		em.flush();
		em.clear();
		
		celulares = em.merge(celulares); // ESTADO DO OBJETO É MANAGED
		em.remove(celulares);
		em.flush();		
		
		em.getTransaction().commit();
		em.close();
	}

}
