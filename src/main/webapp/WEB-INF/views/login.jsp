<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>Web File Share Service</title>
</head>
<body>
    <section class="login" id="login">
        <h1>Login</h1>
        <form method="post" action="/login">
            <div class="txt_field">
                <input type="text" name="id" maxlength="20" required>
                <span></span>
                <label>ID</label>
            </div>
            <div class="txt_field">
                <input type="password" name="password" maxlength="20" required>
                <span></span>
                <label>Password</label>
            </div>
            <div class="pass">Forgot Password?</div>
            <div class="button">
                <input type="submit" value="login" id="loginbtn">
            </div>
            <div class="signup_link">
                Not a member? <a href="#">Sign Up</a>
            </div>
            </form>
    </section>
</body>
</html>
