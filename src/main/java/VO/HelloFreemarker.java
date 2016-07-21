package main.java.VO;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.BO.Utente;

/**
 * Servlet implementation class HelloFreemarker
 */
@WebServlet("/HelloFreemarker")
public class HelloFreemarker extends HttpServlet {
	
	static final Logger logger = LogManager.getLogger(HelloFreemarker.class.getName());
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloFreemarker() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub

	    logger.info("Inside Hello Logger!");
	    
		// creo la lista di utenti
		ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
		
		listaUtenti.add(new  Utente("Pasquale Costanzo","Developers"));
		listaUtenti.add(new  Utente("Michele Molinari","Developers"));
		 
		//Put the user list in request and let freemarker paint it.
		request.setAttribute("utenti", listaUtenti);
		request.getRequestDispatcher("/index.ftl").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
