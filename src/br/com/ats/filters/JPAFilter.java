/**
 * @author João Sizílio <joao.sizilio@atsinformatica.com.br>
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
	 * Método init() (1)
	 * <p>
	 * As unidades de persistência devem ser inicializadas antes de serem utilizadas. A inicialização [2]
	 * deve ser realizada apenas uma vez durante a execução da aplicação. O método init() é chamado logo no 
	 * início da aplicação e é responsável por criar um EntityManagerFactory.
	 */
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.Emf = Persistence.createEntityManagerFactory("mysql-pu");
	}
	
	/**
	 * Método destroy() (1)
	 * <p>
	 * As unidades de persistência devem ser finalizadas quando não forem mais necessárias. A finalização [2]
	 * deve ser realizada apenas uma vez durante a execução da aplicação. O método destroy() é chamado no
	 * encerramento da aplicação, ele é responsável por fechar o EntityManagerFactory.
	 */
	
	@Override
	public void destroy(){
		this.Emf.close();
	}
	
	/**
	 * Método doFilter() - Interceptador de funções do EntityManager. (1)
	 * <p>
	 * O método doFilter() é chamado toda vez que uma requisição é realizada para a servlet Faces Servlet. [2]
	 * <p>
	 * Antes de repassar a requisição para a Faces Servlet, o método doFilter() cria um EntityManager, armazena-o 
	 * na requisição e abre uma transação. Depois que a Faces Servlet	processou a requisição, o método doFilter() 
	 * tenta confirmar a transação através do método commit(). Se um erro ocorrer nessa tentativa, o método 
	 * rollback() é chamado para cancelar a transação.
	 * <p>
	 * <i>Esta implementação dispensa o uso do getTransaction().begin() e do getTransaction().commit() na aplicação</i>
	 * <p>
	 * O EntityManager armazenado dentro da requisição pelo filtro pode ser recuperado a qualquer momento
	 * durante o processamento da requisição através do código:
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
		
		//Saída
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}		
	}	

}
