package in.sp.backend.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import in.sp.backend.connection.DbCon;

@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            Connection con = DbCon.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM orders WHERE id=?");

            ps.setInt(1, id);

            ps.executeUpdate();

            response.sendRedirect("orders.jsp");

        } catch(Exception e){
            e.printStackTrace();
        }

    }

}