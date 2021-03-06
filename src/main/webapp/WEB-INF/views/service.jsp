<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <title>Web File Share Service</title>
</head>
<body>
<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <thead>
            <tr>
                <th style="background-color: #eeeeee; text-align: center;">번호</th>
                <th style="background-color: #eeeeee; text-align: center;">파일명</th>
                <th style="background-color: #eeeeee; text-align: center;">파일크기</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${FileList}" var="list">
                <tr>
                    <td><a href="service/delete?num=${list.num}"/>${list.num}</td>
                    <td><a href="service/download?num=${list.num}"/>${list.filename}</td>
                    <td>${list.size}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:set var="userID" value="${userID}"/>
    <c:if test= "${userID != null}">
    <form method="post" enctype="multipart/form-data">
        <h3>파일 업로드</h3>
        <label><input multiple="multiple" type="file" name="data"></label>
        <input type="submit" class="btn btn-success" value="업로드">
    </form>
    </c:if>
</div>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
