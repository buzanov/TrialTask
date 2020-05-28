<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
<body>
<form action="/signIn" method="post">
    <label>Login:
        <input type="text" name="login">
    </label>
    <label>Password:
        <input type="password" name="password">
    </label>
    <input type="submit" value="Login">
</form>
<a href="https://oauth.vk.com/authorize?client_id=7461226&redirect_uri=http://localhost/signIn&display=popup&response_type=code">
    Login with vk</a>
</body>
</html>