/**
 * @author João Sizílio <joao.sizilio@atsinformatica.com.br>
 * @version 1.0
 * @since 2013-11-07
 */

package br.com.ats.testes;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class GeradorDeTabelas {
	/**
	 * Gerador de tabelas - Classe do pacote Teste (1)
	 * <p>
	 * Esta classe é responsável por conectar no banco de dados através do Hibernate e gerar as [2]
	 * tabelas mapeadas nas classes beans.
	 * <p>
	 * Utilize essa classe para testar se as configurações no persistence.xml estão corretamente 
	 * configuradas e se as tabelas geradas no bd estão como desejado.	
	 */
	
	public static void main ( String [] args ) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-pu");		
		emf.close();
	}
}
