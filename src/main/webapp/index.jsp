<%--
  Created by IntelliJ IDEA.
  User: yuzho
  Date: 2020/8/17
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: yuzho
  Date: 2020/9/9
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final String path = request.getContextPath();
%>
<html>
<head>
    <title>登录</title>
    <link rel="icon" href="<%=path%>/img/favicon.png" type="image/png"/>
    <link  rel="stylesheet" type="text/css" scope="prototype" href="<%=path%>/css/Index.css">
    <link  rel="stylesheet" type="text/css" scope="prototype" href="<%=path%>/font-awesome-4.7.0/css/font-awesome.css">
</head>
<body>

<div class="login">
    <h1>办公系统</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">

        <div class="form">
            <div class="item">
                <i class="fa fa-user-circle" aria-hidden="true"></i>
                <input type=text name="username" id="username" placeholder="Username" >
            </div>
            <div class="item">
                <i class="fa fa-key" aria-hidden="true"></i>
                <input type=password  name="password" id="password" placeholder="Password" >
            </div>
        </div>
        <button type="submit">Login</button>
    </form>

</div>
</body>
</html>


