<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>SIGN IN</h1>
<form action="/signIn" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <label>
        <input name="email" type="email" placeholder="EMAIL">
    </label>
    <label>
        <input name="password" type="password" placeholder="PASSWORD">
    </label>
    <label>
        <input type="checkbox" name="remember-me">remember me
    </label>
    <input type="submit" value="SIGN IN">
</form>
</body>
</html>
