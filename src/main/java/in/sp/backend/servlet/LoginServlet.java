package in.sp.backend.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import in.sp.backend.connection.DbCon;
import in.sp.backend.dao.UserDao;
import in.sp.backend.model.User;


@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("login.jsp");
 	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		response.setContentType("text/html;charset=UTF-8");
		
		
		try(PrintWriter out=response.getWriter()){
			String email= request.getParameter("login-email");
			String password = request.getParameter("login-password"); 
			
			
			try {
				UserDao udao = new UserDao(DbCon.getConnection());
				User user = udao.userLogin(email, password);
				
			if(user!=null) {
				out.print("user login");
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			}else {
				out.print("user login failed");
			}
		} catch (ClassNotFoundException | SQLException e) {
			 
			e.printStackTrace();
		}
	}

	}

}

























