package main.java.BO;

/**
 * 
 * @author Pasquale
 *
 */

public class Utente {


	private String nome, lavoro;
	
	
	/**
	 * 
	 * @param nome dell'utente
	 * @param lavoro dell'utente
	 */
	
	public Utente(String nome, String lavoro) {
		super();
		this.nome = nome;
		this.lavoro = lavoro;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLavoro() {
		return lavoro;
	}

	public void setLavoro(String lavoro) { 
		this.lavoro = lavoro;
	}

}
