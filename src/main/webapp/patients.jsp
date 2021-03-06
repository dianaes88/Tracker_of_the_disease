<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://tracker.javaweb.ru/functions" %>


<html>
<head>
    <title>Patients list</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Patients</h2>
    <a href="patients?action=create">Add Patient</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Middle Name</th>
            <th>Surname</th>
            <th>Insurance Id</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${patients}" var="patient">
            <jsp:useBean id="patient" scope="page" type="ru.javaweb.tracker.model.Patient"/>
            <tr>
                <td>${patient.firstName}</td>
                <td>${patient.middleName}</td>
                <td>${patient.lastName}</td>
                <td>${patient.insuranceId}</td>
                <td><a href="patients?action=update&id=${patient.id}">Update</a></td>
                <td><a href="patients?action=delete&id=${patient.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
