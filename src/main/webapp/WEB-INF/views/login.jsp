<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <title>Web File Share Service</title>
</head>
<body>
<div class="container" style="text-align: center">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <div class="jumbotron" style="padding-top: 20px;">
            <form method="post">
                <h3 style="text-align: center;">로그인을 해주세요.</h3>
                <div class="form-group">
                    <input type="id" class="form-control" placeholder="아이디" name="id" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20">
                </div>
                <input type="submit" class="btn btn-primary form-control" value="로그인">
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
