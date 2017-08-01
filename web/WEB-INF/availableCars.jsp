<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Available Cars</title>
</head>
<body>
    <a href="/myCabinet">Back to My Cabinet</a> <br>
    <div align="center">
        <table border="1">
            <tr>
                <td align="center">Car ID</td>
                <td align="center">Brand</td>
                <td align="center">Model</td>
                <td align="center">Color</td>
                <td align="center">$/day</td>
                <td></td>
            </tr>
            <c:forEach items="${requestScope.cars}" var="car">
                <tr>
                    <td align="center">${car.id}</td>
                    <td align="center"><c:out value="${car.brand}"/></td>
                    <td align="center"><c:out value="${car.model}"/></td>
                    <td align="center"><c:out value="${car.color}"/></td>
                    <td align="center"><c:out value="${car.pricePerDay}"/></td>
                    <td align="center">
                        <a href="rentCar?id=${car.id}">
                            <img src="../rent.png" width="43px" height="30px"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>