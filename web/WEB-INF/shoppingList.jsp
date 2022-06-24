<%-- 
    Document   : shoppingList
    Created on : 24-Jun-2022, 4:40:22 PM
    Author     : Vaibhav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username}</p> <a href='<c:url value="shopping?action=logout"/>'>Logout</a>
        <h2>List</h2>
        <form method="post" action="shopping">
            <label>Add item:</label>
            <input type="text" name="listItem">
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>

        <c:if test = "${show}">
        <form method="post" action="shopping">
            <c:forEach var="shoppingList" items="${shoppingList}">
                <input type="radio" name="itemDel" value="${shoppingList}">${shoppingList}<br>
            </c:forEach>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
        </c:if>
        
    </body>
</html>
