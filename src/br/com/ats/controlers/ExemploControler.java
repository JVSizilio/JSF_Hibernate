/**
 * @author Jo�o Siz�lio <joao.sizilio@atsinformatica.com.br>
 * @version 1.0
 * @since 2013-11-07
 */

package br.com.ats.controlers;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.ats.beans.ExemploBean;
import br.com.ats.daos.ExemploDao;

@ManagedBean
public class ExemploControler {
	
	/**
	 * Classe ExemploControler(1)
	 * <p>
	 * Os m�todos das classes Controlers, dever�o utilizar os m�todos das classes Daos para as requisi��es  [2]
	 * no banco de dados. Esses m�todos dever�o enviar uma EntityManager para a realiza��o da opera��o.	 * 
	 */
	
	private ExemploBean exemplo = new ExemploBean();
	
	
	public void adicionaExemplo(){
		EntityManager em = this.getEntityManager();
		ExemploDao dao = new ExemploDao(em);
		dao.adiciona(this.exemplo);
		this.exemplo = new ExemploBean();
	}
	
	public List<ExemploBean> getExemplos() {
		EntityManager em = this.getEntityManager();
		ExemploDao dao = new ExemploDao(em);
		return dao.buscaTodos();
	}	
	
	private EntityManager getEntityManager () {
		FacesContext fc = FacesContext . getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager ;
	}

	public ExemploBean getExemplo() {
		return this.exemplo;
	}

	public void setExemplo(ExemploBean exemplo) {
		this.exemplo = exemplo;
	}
	
	
}
