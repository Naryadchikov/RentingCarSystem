<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Form</title>
</head>
    <body>
        <div align="center">
            <form action="/sendOrder" method="POST">
                passport number : <input name="passport" type="text" required="required"> <br>
                start date : <input name="startDate" type="date" required="required"> <br>
                ending date : <input name="endingDate" type="date" required="required"> <br>
                <input type="submit" value="Send the order">
            </form>
        </div>
    </body>
</html>