<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current Orders</title>
</head>
    <body>
        <a href="/adminCabinet">Back to Admin Cabinet</a> <br>
        <div align="center">
            <table border="1">
                <tr>
                    <td align="center">Order ID</td>
                    <td align="center">Car ID</td>
                    <td align="center">User ID</td>
                    <td align="center">Passport</td>
                    <td align="center">Start date</td>
                    <td align="center">Ending date</td>
                    <td align="center">Status</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach items="${requestScope.orders}" var="order">
                    <tr>
                        <td align="center">${order.id}</td>
                        <td align="center"><c:out value="${order.carId}"/></td>
                        <td align="center"><c:out value="${order.userId}"/></td>
                        <td align="center"><c:out value="${order.passport}"/></td>
                        <td align="center"><c:out value="${order.startDate}"/></td>
                        <td align="center"><c:out value="${order.endingDate}"/></td>
                        <td align="center"><c:out value="${order.status}"/></td>
                        <td align="center">
                            <a href="orderAccepted?id=${order.id}">
                                <img src="../accept.png" width="40px" height="40px"/>
                            </a>
                        </td>
                        <td align="center">
                            <a href="orderDeclined?id=${order.id}">
                                <img src="../decline.png" width="40px" height="40px"/>
                            </a>
                        </td>
                        <td align="center">
                            <a href="makeReport?id=${order.id}">
                                <img src="../report.png" width="40px" height="40px"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>