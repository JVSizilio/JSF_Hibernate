/**
 * @author Jo�o Siz�lio <joao.sizilio@atsinformatica.com.br>
 * @version 1.0
 * @since 2013-11-06
 */

package br.com.ats.filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames={"Faces Servlet"})
public class JPAFilter implements Filter{

	private EntityManagerFactory Emf;
	
	/**
	 * M�todo init() (1)
	 * <p>
	 * As unidades de persist�ncia devem ser inicializadas antes de serem utilizadas. A inicializa��o [2]
	 * deve ser realizada apenas uma vez durante a execu��o da aplica��o. O m�todo init() � chamado logo no 
	 * in�cio da aplica��o e � respons�vel por criar um EntityManagerFactory.
	 */
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.Emf = Persistence.createEntityManagerFactory("mysql-pu");
	}
	
	/**
	 * M�todo destroy() (1)
	 * <p>
	 * As unidades de persist�ncia devem ser finalizadas quando n�o forem mais necess�rias. A finaliza��o [2]
	 * deve ser realizada apenas uma vez durante a execu��o da aplica��o. O m�todo destroy() � chamado no
	 * encerramento da aplica��o, ele � respons�vel por fechar o EntityManagerFactory.
	 */
	
	@Override
	public void destroy(){
		this.Emf.close();
	}
	
	/**
	 * M�todo doFilter() - Interceptador de fun��es do EntityManager. (1)
	 * <p>
	 * O m�todo doFilter() � chamado toda vez que uma requisi��o � realizada para a servlet Faces Servlet. [2]
	 * <p>
	 * Antes de repassar a requisi��o para a Faces Servlet, o m�todo doFilter() cria um EntityManager, armazena-o 
	 * na requisi��o e abre uma transa��o. Depois que a Faces Servlet	processou a requisi��o, o m�todo doFilter() 
	 * tenta confirmar a transa��o atrav�s do m�todo commit(). Se um erro ocorrer nessa tentativa, o m�todo 
	 * rollback() � chamado para cancelar a transa��o.
	 * <p>
	 * <i>Esta implementa��o dispensa o uso do getTransaction().begin() e do getTransaction().commit() na aplica��o</i>
	 * <p>
	 * O EntityManager armazenado dentro da requisi��o pelo filtro pode ser recuperado a qualquer momento
	 * durante o processamento da requisi��o atrav�s do c�digo:
	 * <p>
	 *	FacesContext fc = FacesContext.getCurrentInstance();
	 *	ExternalContext ec = fc.getExternalContext();
	 *	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	 *	EntityManager manager = (EntityManager) request.getAttribute("EntityManager");	
	 */
	
	@Override
	public void doFilter(ServletRequest request ,ServletResponse response, FilterChain chain)
			throws IOException ,ServletException {
		
		//Entrada
		EntityManager em = this.Emf.createEntityManager();
		request.setAttribute("EntityManager", em);
		em.getTransaction().begin();
		
		//Faces Servlet
		chain.doFilter(request, response);
		
		//Sa�da
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}		
	}	

}
