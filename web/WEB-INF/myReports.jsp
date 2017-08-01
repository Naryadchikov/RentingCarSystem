<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Reports</title>
</head>
    <body>
        <a href="/myCabinet">Back to My Cabinet</a> <br>
        <div align="center">
            <table border="1">
                <tr>
                    <td align="center">Fine</td>
                    <td align="center">Comment</td>
                </tr>
                <c:forEach items="${requestScope.reports}" var="report">
                    <tr>
                        <td align="center"><c:out value="${report.fine}"/></td>
                        <td align="center"><c:out value="${report.comment}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>