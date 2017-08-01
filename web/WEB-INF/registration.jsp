<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <div align="center">
        <form action="/addUser" method="POST">
            email address : <input name="email" type="email" required="required">
            password : <input name="pass" type="password" required="required">
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
