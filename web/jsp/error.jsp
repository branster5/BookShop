<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <%@ page isErrorPage="true" %>
        <%
            String msg = (String) request.getAttribute("result");
            out.print("<h3>" + msg + "</h3>");
            session.invalidate();
        %>
        <jsp:include page="footer.jsp" />
    </body>
</html>