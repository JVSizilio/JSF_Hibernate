/**
 * @author Jo�o Siz�lio <joao.sizilio@atsinformatica.com.br>
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
	 * As classes Daos devem conter os m�todos da EntityManager necess�rios para cada uma, lembrando que [2]
	 * os m�todos de inicializa��o e finaliza��o da EntityManager, incluindo o m�todo commit, n�o s�o mais 
	 * necess�rios devido ao uso da classe br.com.ats.filters/JPAFilter.
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
