<%@ page import="in.sp.backend.connection.DbCon"%>
<%@ page import="in.sp.backend.model.*"%>
<%@ page import="in.sp.backend.dao.ProductDao"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
DecimalFormat dcf = new DecimalFormat("#.##");

User auth = (User) session.getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;

double total = 0;

if (cart_list != null) {
	ProductDao pDao = new ProductDao(DbCon.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	total = pDao.getTotalCartPrice(cart_list);
}
%>

<!DOCTYPE html>
<html>

<head>

<title>Your Shopping Cart</title>

<%@include file="includes/header.jsp"%>

<style>
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 20px;
}
</style>

</head>

<body>

	<%@include file="includes/navbar.jsp"%>

	<div class="container my-5">

		<h2 class="text-center mb-4">🛒 Your Shopping Cart</h2>

		<div class="card shadow">

			<div class="card-body">

				<div class="d-flex justify-content-between mb-3">

					<h4>
						Total Price : ₹
						<%=dcf.format(total)%></h4>

					<a href="check-out" class="btn btn-success">Checkout</a>
				</div>

				<table class="table table-light">

					<thead class="table-dark">

						<tr>
							<th>Image</th>
							<th>Name</th>
							<th>Category</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Action</th>
						</tr>

					</thead>

					<tbody>

						<%
						if (cartProduct != null) {
							for (Cart c : cartProduct) {
						%>

						<tr>

							<td><img
								src="<%=request.getContextPath()%>/product-images/<%=c.getImage()%>"
								width="60"></td>

							<td><%=c.getName()%></td>

							<td><%=c.getCategory()%></td>

							<td>₹<%=dcf.format(c.getPrice())%></td>

							<td><a class="btn btn-sm btn-incre"
								href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"> - </a> <input
								type="text" class="form-control text-center"
								value="<%=c.getQuantity()%>" readonly
								style="width: 60px; display: inline-block"> <a
								class="btn btn-sm btn-decre"
								href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"> + </a></td>

							<td><a
								href="buy-now?id=<%=c.getId()%>&quantity=<%=c.getQuantity()%>"
								class="btn btn-primary">Buy</a> <a
								href="remove-from-cart?id=<%=c.getId()%>"
								class="btn btn-sm btn-danger">Remove</a></td>

						</tr>

						<%
						}
						}
						%>

					</tbody>

				</table>

			</div>

		</div>

	</div>

	<%@include file="includes/footer.jsp"%>

</body>

</html>