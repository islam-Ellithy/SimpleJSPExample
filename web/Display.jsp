<%-- 
    Document   : Display
    Created on : Oct 7, 2017, 1:45:03 PM
    Author     : MrHacker
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><% HashMap<String, String> mp = (HashMap) session.getAttribute("obj");

            String exp = mp.get("exp");

            for (int i = 0; i < exp.length(); i++) {
                char c = exp.charAt(i);
                if (c == '+' || c == '-') {
                    out.print("<font color=\"red\">" + c + "</font>");

                } else if (c == '*' || c == '/') {
                    out.print("<font color=\"blue\">" + c + "</font>");
                } else {
                    out.print(c);
                }
            }

            out.print("<br>" + mp.get("result"));

            %></h1>

    </body>
</html>
