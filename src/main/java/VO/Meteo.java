package main.java.VO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.Utils;

@WebServlet("/meteo")
public class Meteo extends HttpServlet {
	
	static final Logger logger = LogManager.getLogger(Meteo.class.getName());
	
	Utils utils = new Utils();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/meteo.ftl").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String city = req.getParameter("city");
		
		utils.doCall("http://www.google.it", "GET", "");
		
		logger.info("Città: " +city);
		
		req.getRequestDispatcher("/meteo.ftl").forward(req, resp);
		
	}

}
