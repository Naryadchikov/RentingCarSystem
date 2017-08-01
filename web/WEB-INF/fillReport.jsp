<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report Form</title>
</head>
<body>
    <div align="center">
        <form action="/sendReport" method="POST">
            fine : <input name="fine" type="number" required="required"> <br>
            comment : <input name="comment" type="text" required="required"> <br>
            <input type="submit" value="Send report">
        </form>
    </div>
</body>
</html>