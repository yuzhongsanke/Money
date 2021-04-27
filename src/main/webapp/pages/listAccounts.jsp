<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 闫卫新
  Date: 2019/7/4
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账户列表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#addBtn").click(function () {
                location = "${pageContext.request.contextPath}/account/addAccountUI";
            });

            $("#install").click(function () {
                location = "${pageContext.request.contextPath}/account/install";
            });


            $("#delBtn").click(function () {

                var aidsLength = $("#fo input[type='checkbox']:checked").length;
                if (aidsLength == 0) {
                    alert("请先选择要删除的记录")
                    return;
                }
                var sure = confirm("您确定要删除所选记录吗？")
                if (sure) {
                    $("#fo").submit();
                }
            });
        })
    </script>
</head>
<body>
<h2>账户列表</h2>
<input type="button" id="addBtn" value="添加">
<input type="button" id="delBtn" value="删除">
<input type="button" id="install" value="下载">

<form id="fo" method="post" action="${pageContext.request.contextPath}/account/delAccounts">
    <table border="1" width="78%">
        <thead>
        <tr>
            <th>选择</th>
            <th>序号</th>
            <th>账号</th>
            <th>余额</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${accounts}" var="account" varStatus="vs">
            <tr>
                <td>
                    <input type="checkbox" name="aids" value="${account.aid}">
                </td>
                <td>${vs.count}</td>
                <td>${account.number}</td>
                <td>${account.balance}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/account/editAccountUI?aid=${account.aid}">修改</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    第一个用户：${accounts[0].number}<br>
    第二个用户：${accounts[1].number}<br>


</form>

</body>
</html>
