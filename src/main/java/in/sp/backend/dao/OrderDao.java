package in.sp.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.sp.backend.model.Order;

public class OrderDao {

    private Connection con;
    private PreparedStatement pst;

    public OrderDao(Connection con) {
        this.con = con;
    }

    public boolean insertOrder(Order order) {

        boolean result = false;

        try {

            String query = "insert into orders(p_id, u_id, quantity, date) values(?,?,?,?)";

            pst = con.prepareStatement(query);

            pst.setInt(1, order.getId());
            pst.setInt(2, order.getUdi());
            pst.setString(3, order.getQuantity());
            pst.setString(4, order.getDate());

            pst.executeUpdate();

            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}