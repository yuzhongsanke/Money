    <%--
  Created by IntelliJ IDEA.
  User: 闫卫新
  Date: 2019/7/5
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改账户</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/account/editAccount">
    <input type="hidden" name="aid" value="${account.aid}"><br>
    账号：<input name="number" value="${account.number}"/><br>
    余额：<input name="balance" value="${account.balance}"><br>
    <input type="submit" value="保存">
</form>

</body>
</html>
