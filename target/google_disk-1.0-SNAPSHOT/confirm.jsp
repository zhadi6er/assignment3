<%--
  Created by IntelliJ IDEA.
  User: ismail
  Date: 10/18/20
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Hello</p>
Stats:
How many times you have entered <%=request.getAttribute("counter")%><br>
Session was created <%=request.getAttribute("lastTime")%><br>
Last Visited Time <%=request.getAttribute("lastVisitedTime")%><br>
</body>
</html>
