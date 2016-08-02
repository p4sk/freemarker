package main.java.VO;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import main.java.BO.Utente;
import utils.Utils;

@WebServlet("/hellofreemarker")
public class HelloFreemarker extends HttpServlet {

	static final Logger logger = LogManager.getLogger(HelloFreemarker.class.getName());

	private static final long serialVersionUID = 1L;

	Utils utils = new Utils();

	Utente utente = new Utente("","");

	public HelloFreemarker() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// creo la lista di utenti
		LinkedList<Utente> listaUtenti = new LinkedList<Utente>();

		logger.info("Inizio");
		
		JSONArray jArr = (JSONArray)utils.doCall("http://jsonplaceholder.typicode.com/users", "GET", null);
		
		Iterator<?> iterator = jArr.iterator();
		JSONObject jOb = new JSONObject();
		
		/*
		 * Itero la response e recupero il name 
		 */
		while (iterator.hasNext()) {
			jOb=(JSONObject) iterator.next();
			String name = (String)jOb.get("name");
			Utente utente = new Utente(name, null);
			logger.info("UTENTE: " +utente.getNome());
			listaUtenti.add(utente);
		}
		logger.info("ListaUtenti: " +listaUtenti);
		
		/*
		 * Setto la lista degli utenti nella request e la visualizzo tramite freemarker.
		 */
		Utente u = new Utente("Pask", "Programmatore");
		request.setAttribute("provaUtente", u);
		
		request.setAttribute("utenti", listaUtenti);

		request.getRequestDispatcher("/index.ftl").forward(request, response);

		
		
		logger.info("Fine");
		logger.info("*****");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/error.ftl").forward(request, response);

	}

}
