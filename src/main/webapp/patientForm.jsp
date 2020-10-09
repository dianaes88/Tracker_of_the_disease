<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Patient</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create patient' : 'Edit patient'}</h2>
    <hr>
    <jsp:useBean id="patient" type="ru.javaweb.tracker.model.Patient" scope="request"/>
    <form method="post" action="patients">
        <input type="hidden" name="id" value="${patient.id}">
        <dl>
            <dt>First Name:</dt>
            <dd><input type="text" value="${patient.firstName}" name="firstName" required></dd>
        </dl>
        <dl>
            <dt>Middle Name:</dt>
            <dd><input type="text" value="${patient.middleName}" name="middleName"></dd>
        </dl>
        <dl>
            <dt>Last Name:</dt>
            <dd><input type="text" value="${patient.lastName}" name="lastName" required></dd>
        </dl>
        <dl>
            <dt>Insurance id:</dt>
            <dd><input type="number" value="${patient.insuranceId}" name="insuranceId"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>