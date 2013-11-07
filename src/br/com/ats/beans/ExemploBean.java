/**
 * @author João Sizílio <joao.sizilio@atsinformatica.com.br>
 * @version 1.0
 * @since 2013-11-07
 */

package br.com.ats.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="exemplo")
public class ExemploBean implements Serializable {
	
	/**
	 * Classe ExemploBean (1)
	 * <p>
	 * As classes beans devem conter apenas os atributos necessários, os métodos getters/setters e o método [2]
	 * toString(), como mostrado aqui na classe ExemploBean.
	 * <p>
	 * Faz-se necessário também mapear a classe com as corretas marcações do hibernate;
	 * 
	 */

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String nome;
	
	@Column
	private String email;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString(){
		return "Exemplo [id=" + id + ", nome=" + nome + ", email="
				+ email + "]";
	}
}
