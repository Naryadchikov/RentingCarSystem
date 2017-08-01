<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current orders</title>
</head>
    <body>
        <a href="/myCabinet">Back to My Cabinet</a> <br>
        <div align="center">
            <table border="1">
                <tr>
                    <td align="center">Car ID</td>
                    <td align="center">Start date</td>
                    <td align="center">Ending date</td>
                    <td align="center">Status</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach items="${requestScope.orders}" var="order">
                    <tr>
                        <td align="center"><c:out value="${order.carId}"/></td>
                        <td align="center"><c:out value="${order.startDate}"/></td>
                        <td align="center"><c:out value="${order.endingDate}"/></td>
                        <td align="center"><c:out value="${order.status}"/></td>
                        <td align="center">
                            <a href="carPayment?id=${order.id}">
                                <img src="../pay.jpg" width="40px" height="40px"/>
                            </a>
                        </td>
                        <td align="center">
                            <a href="carReturning?id=${order.id}">
                                <img src="../return.png" width="40px" height="40px"/>
                            </a>
                        </td>
                        <td align="center">
                            <a href="declineOrder?id=${order.id}">
                                <img src="../decline.png" width="40px" height="40px"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>