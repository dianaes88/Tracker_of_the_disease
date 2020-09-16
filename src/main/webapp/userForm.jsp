<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create user' : 'Edit user'}</h2>
    <hr>
    <jsp:useBean id="user" type="ru.javaweb.tracker.model.User" scope="request"/>
    <form method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
            <dt>First Name:</dt>
            <dd><input type="text" value="${user.firstName}" name="firstName"></dd>
        </dl>
        <dl>
            <dt>Middle Name:</dt>
            <dd><input type="text" value="${user.middleName}" name="middleName"></dd>
        </dl>
        <dl>
            <dt>Last Name:</dt>
            <dd><input type="text" value="${user.lastName}" name="lastName"></dd>
        </dl>
        <dl>
            <dt>Position:</dt>
            <dd><input type="text" value="${user.position}" name="position"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>