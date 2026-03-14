package in.sp.backend.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import in.sp.backend.connection.DbCon;
import in.sp.backend.dao.OrderDao;
import in.sp.backend.model.Cart;
import in.sp.backend.model.Order;
import in.sp.backend.model.User;

@WebServlet("/check-out")
public class CheckOutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            User auth = (User) request.getSession().getAttribute("auth");

            if (auth == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            if (cart_list == null || cart_list.size() == 0) {
                response.sendRedirect("cart.jsp");
                return;
            }

            OrderDao oDao = new OrderDao(DbCon.getConnection());

            for (Cart c : cart_list) {

                Order order = new Order();

                order.setId(c.getId());
                order.setUdi(auth.getId());
                order.setQuantity(String.valueOf(c.getQuantity()));
                order.setDate(java.time.LocalDate.now().toString());

                oDao.insertOrder(order);
            }

            cart_list.clear();

            response.sendRedirect("orders.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}