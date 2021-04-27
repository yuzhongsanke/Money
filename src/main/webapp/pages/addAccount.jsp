<%--
  Created by IntelliJ IDEA.
  User: 闫卫新
  Date: 2019/7/5
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加账户</title>
</head>
<body>
<hr>
<form action="${pageContext.request.contextPath}/account/addAccount"method="post">
    账户：<input type="text" name="number"><br>
    余额：<input name="balance"><br>
    <input type="submit" value="保存">
</form>
</body>
</html>
