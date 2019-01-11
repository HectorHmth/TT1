package mx.ipn.escom.mau.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import mx.ipn.escom.mau.bs.LoginBs;
import mx.ipn.escom.mau.entities.Cuenta;
import mx.ipn.escom.utils.SessionObjects;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Autowired
	private LoginBs loginBs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String username = request.getParameter("cuenta.nb_usuario");
			String password = request.getParameter("cuenta.contrasena");
			Cuenta cuenta  = loginBs.login(username, password);
			if(cuenta != null) {
				HttpSession session = request.getSession();
				session.setAttribute(SessionObjects.CUENTA_USUARIO, cuenta);
				RequestDispatcher rd = request.getRequestDispatcher("HomeController");
				rd.forward(request, response);
			}else {
				response.sendRedirect("LoginController");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("LoginController");
		}
	}

}
