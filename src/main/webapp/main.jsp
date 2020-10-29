<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="models.Category" %>
<%@ page import="models.MyList" %>
<%@ page import="models.Item" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Homepage - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">

</head>

<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/main">My Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/main">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/cart?action=3">Cart</a>
                </li>
                <%
                    //Checks if user is entered
                    Cookie[] cookies = request.getCookies();
                    boolean found = false;
                    String user = "";
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("user")){
                            user = cookie.getValue();
                            found = true;
                        }
                    }
                    if (found){
                %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/logOut">Logout(<%=user%>)</a>
                    </li>
                <%}else {%>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/login">Login</a>
                    </li>
                <%}%>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <h1 class="my-4">My Shop</h1>
            <div class="list-group">
                <c:forEach var="category" items="${categories}" >
                    <a href="<%=request.getContextPath()%>/main?category=${category.id}" class="list-group-item">${category.name}</a>
                </c:forEach>
            </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div class="row">
                <%
                    MyList<Item> items = new MyList<>();
                    items = (MyList<Item>) request.getAttribute("items");
                    Set<Item> itemSet = items.getSet();
                %>
                <% for (Item item: itemSet) { %>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h4 class="card-title">
                                <%=item.getName()%>
                            </h4>
                            <h5>$<%=item.getPrice()%></h5>
                            <p class="card-text"><%=item.getDetails()%></p>
                        </div>
                        <div class="card-footer">
                            <a href="<%=request.getContextPath()%>/cart?action=1&id=<%=item.getId()%>">Add to Cart</a>
                        </div>
                    </div>
                </div>
                <%}%>



            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->


<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
