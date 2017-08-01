<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Cabinet</title>
</head>
<body>
    <div align="center">
        <a href="/availableCars">Available Cars</a> <br>
        <a href="/myCurrentOrders">My Current Orders</a> <br>
        <a href="/myReports">My Reports History</a> <br>
        <form action="/signOut" method="POST">
            <input type="submit" value="Sign out">
        </form>
    </div>
</body>
</html>