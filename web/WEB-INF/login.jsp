<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <div align="center">
            <img src="../3cars.jpg" alt=""> <br>
            <form action="/signIn" method="POST">
                email address : <input name="email" type="email" required="required">
                password : <input name="pass" type="password" required="required"> <br>
                <input type="submit" value="Sign in">
            </form>
            <a href="/registration">Registration</a>
        </div>
    </body>
</html>
