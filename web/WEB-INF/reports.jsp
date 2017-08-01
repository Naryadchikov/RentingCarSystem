<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Reports</title>
</head>
<body>
    <a href="/adminCabinet">Back to Admin Cabinet</a> <br>
    <div align="center">
        <table border="1">
            <tr>
                <td align="center">Report ID</td>
                <td align="center">Order ID</td>
                <td align="center">USER ID</td>
                <td align="center">Fine</td>
                <td align="center">Comment</td>
            </tr>
            <c:forEach items="${requestScope.reports}" var="report">
                <tr>
                    <td>${report.id}</td>
                    <td><c:out value="${report.orderId}"/></td>
                    <td><c:out value="${report.userId}"/></td>
                    <td><c:out value="${report.fine}"/></td>
                    <td><c:out value="${report.comment}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>