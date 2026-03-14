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

 
@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public RemoveFromCartServlet() {
        super();
     }

	 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id != null) {

            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            if (cart_list != null) {

                int productId = Integer.parseInt(id);

                for (int i = 0; i < cart_list.size(); i++) {

                    if (cart_list.get(i).getId() == productId) {
                        cart_list.remove(i);
                        break;
                    }

                }

            }

        }

        response.sendRedirect("cart.jsp");
    }



}








