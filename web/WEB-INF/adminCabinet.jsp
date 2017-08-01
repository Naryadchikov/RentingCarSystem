<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Cabinet</title>
</head>
    <body>
        <div align="center">
            <a href="/cars">All Cars</a> <br>
            <a href="/orders">All Orders</a> <br>
            <form action="/signOut" method="POST">
                <input type="submit" value="Sign out">
            </form>
        </div>
    </body>
</html>