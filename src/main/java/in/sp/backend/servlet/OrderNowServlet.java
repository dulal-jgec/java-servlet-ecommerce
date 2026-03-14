package in.sp.backend.servlet;

import in.sp.backend.dao.OrderDao;
import in.sp.backend.connection.DbCon;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import in.sp.backend.model.Order;
import in.sp.backend.model.User;

 
@WebServlet("/buy-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public OrderNowServlet() {
        super();
     }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 try {
				 int id= Integer.parseInt(request.getParameter("id"));
				 String quantity = request.getParameter("quantity");

				 User auth=(User) request.getSession().getAttribute("auth");
				 if(auth!=null) {
					 
					 Order order = new Order();
					 order.setId(id);
					 order.setUdi(auth.getId());
					 order.setQuantity(quantity);					 order.setDate(java.time.LocalDate.now().toString());
					 
					 OrderDao oDao = new OrderDao(DbCon.getConnection());
					 boolean result = oDao.insertOrder(order);

					 if(result){
					     response.sendRedirect("orders.jsp");
					 }else{
					     response.sendRedirect("cart.jsp");
					 }
					 
				 }else {
					 response.sendRedirect("login.jsp");
				 }
				 
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
	
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 		doGet(request, response);
	}

}
