/**
 * @author João Sizílio <joao.sizilio@atsinformatica.com.br>
 * @version 1.0
 * @since 2013-11-07
 */
package br.com.ats.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.ats.beans.ExemploBean;

public class ExemploDao {
	
	/**
	 * Classe ExemploDao (1)
	 * <p>
	 * As classes Daos devem conter os métodos da EntityManager necessários para cada uma, lembrando que [2]
	 * os métodos de inicialização e finalização da EntityManager, incluindo o método commit, não são mais 
	 * necessários devido ao uso da classe br.com.ats.filters/JPAFilter.
	 */
	
	private EntityManager Em;	
	
	public ExemploDao (EntityManager em){
		this.Em = em;
	}
	
	public void adiciona (ExemploBean cliente){
		this.Em.persist(cliente);		
	}
	
	public List<ExemploBean> buscaTodos(){
		Query query = this.Em.createQuery("Select x from ExemploBean x");
		return query.getResultList();
	}
	
	
}
