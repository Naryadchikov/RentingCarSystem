<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars List</title>
</head>
    <body>
        <a href="/adminCabinet">Back to Admin Cabinet</a> <br>
        <div align="center">
            <table border="1">
                <tr>
                    <td align="center">Car ID</td>
                    <td align="center">Brand</td>
                    <td align="center">Model</td>
                    <td align="center">Color</td>
                    <td align="center">$/day</td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach items="${requestScope.cars}" var="car">
                    <tr>
                        <td>${car.id}</td>
                        <td><c:out value="${car.brand}"/></td>
                        <td><c:out value="${car.model}"/></td>
                        <td><c:out value="${car.color}"/></td>
                        <form action="/updateCar?id=${car.id}" method="POST">
                            <td><input name="pricePerDay" value="${car.pricePerDay}" type="number"></td>
                            <td><input type="submit" value="Update"></td>
                        </form>
                        <td>
                            <a href="deleteCar?id=${car.id}">
                                <img src="../delete.png" width="30px" height="30px"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <form action="/addCar" method="POST">
                    <tr>
                        <td colspan="2">
                            <input name="brand" type="text">
                        </td>
                        <td><input name="model" type="text"></td>
                        <td><input name="color" type="text"></td>
                        <td colspan="2">
                            <input name="pricePerDay" type="number">
                        </td>
                        <td><input type="submit" value="Add Car"></td>
                    </tr>
                </form>
            </table>
        </div>
    </body>
</html>