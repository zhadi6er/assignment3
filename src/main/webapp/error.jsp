<%--
  Created by IntelliJ IDEA.
  User: ismail
  Date: 9/26/20
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <div id="main">
        <div class="fof">
            <h1>
                <%
                switch ((int) request.getAttribute("error")){
                    case 1:
                        out.print("Check login or/and password");
                        break;
                    case 2:
                        out.print("Please log in");
                        break;
                    case 3:
                        out.print("Login is already taken");
                        break;
                    case 4:
                        out.print("Done!");
                        break;
                    default:
                        out.print("Error!");
                        break;
                }
                %>
            </h1>
        </div>
    </div>
</body>
<style>
    *{
        transition: all 0.6s;
    }

    html {
        height: 100%;
    }

    body{
        font-family: 'Lato', sans-serif;
        color: #888;
        margin: 0;
    }

    #main{
        display: table;
        width: 100%;
        height: 100vh;
        text-align: center;
    }

    .fof{
        display: table-cell;
        vertical-align: middle;
    }

    .fof h1{
        font-size: 50px;
        display: inline-block;
        padding-right: 12px;
        animation: type .5s alternate infinite;
    }

    @keyframes type{
        from{box-shadow: inset -3px 0px 0px #888;}
        to{box-shadow: inset -3px 0px 0px transparent;}
    }
</style>
</html>
