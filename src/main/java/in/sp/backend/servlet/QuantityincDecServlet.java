package in.sp.backend.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import in.sp.backend.model.Cart;
@WebServlet("/quantity-inc-dec")
public class QuantityincDecServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));

		ArrayList<Cart> cart_list =
				(ArrayList<Cart>) request.getSession().getAttribute("cart-list");

		for(Cart c : cart_list){

			if(c.getId() == id){

				if(action.equals("inc")){
					c.setQuantity(c.getQuantity() + 1);
				}

				if(action.equals("dec") && c.getQuantity() > 1){
					c.setQuantity(c.getQuantity() - 1);
				}

			}

		}

		response.sendRedirect("cart.jsp");
	}
}




























