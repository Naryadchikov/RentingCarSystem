<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Declined Report Form</title>
</head>
    <body>
        <div align="center">
            <form action="/sendDeclinedReport" method="POST">
                comment : <input name="comment" type="text" required="required"> <br>
                <input type="submit" value="Send report">
            </form>
        </div>
    </body>
</html>