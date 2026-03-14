 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">

			<!-- Logo -->
			<a class="navbar-brand fw-bold" href="index.jsp">ShoppingCart</a>

			<!-- Toggle Button (Mobile) -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- Navbar Items -->
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarSupportedContent">
				<ul class="navbar-nav">

					<li class="nav-item"><a class="nav-link active" href="index.jsp">Home</a>
					</li>

					 

					<li class="nav-item"><a class="nav-link" href="cart.jsp"><span class="badge badge-danger px-1">${cart_list.size()}</span>  Cart</a></li>
					<%  
					if(auth!=null){%>
						<li class="nav-item"><a class="nav-link" href="orders.jsp">Oders</a></li>
						<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
						

					<%}else{ %>
						<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>

					<% }%>
				 
				

				</ul>
			</div>

		</div>
	</nav>
