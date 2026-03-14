<%@ page import="java.sql.*"%>
<%@ page import="in.sp.backend.connection.DbCon"%>
<%@ page import="in.sp.backend.model.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
User auth = (User) session.getAttribute("auth");

if (auth == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>

<html>

<head>
<title>Your Orders</title>
<%@include file="includes/header.jsp"%>
</head>

<body>

	<%@include file="includes/navbar.jsp"%>

	<div class="container mt-5">

		<h3 class="text-center mb-4">Your Orders</h3>

		<table class="table table-bordered">

			<thead class="table-dark">
				<tr>
					<th>Order ID</th>
					<th>Product ID</th>
					<th>User ID</th>
					<th>Quantity</th>
					<th>Date</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>

				<%
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;

				try {

					con = DbCon.getConnection();

					ps = con.prepareStatement("SELECT * FROM orders WHERE u_id=?");
					ps.setInt(1, auth.getId());

					rs = ps.executeQuery();

					boolean hasOrders = false;

					while (rs.next()) {
						hasOrders = true;
				%>

				<tr>
					<td><%=rs.getInt("id")%></td>
					<td><%=rs.getInt("p_id")%></td>
					<td><%=rs.getInt("u_id")%></td>
					<td><%=rs.getInt("quantity")%></td>
					<td><%=rs.getString("date")%></td>

					<td><a href="cancel-order?id=<%=rs.getInt("id")%>"
						class="btn btn-danger btn-sm"> Cancel </a></td>

				</tr>

				<%
				}

				if (!hasOrders) {
				%>

				<tr>
					<td colspan="6" class="text-center">No Orders Found</td>
				</tr>

				<%
				}

				} catch (Exception e) {
				e.printStackTrace();
				}
				%>

			</tbody>

		</table>

	</div>

	<%@include file="includes/footer.jsp"%>

</body>
</html>
