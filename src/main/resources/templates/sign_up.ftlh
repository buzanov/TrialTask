<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body>
<form action="/signUp" method="post">
    <label>Login:
        <input type="text" name="login">
    </label>
    <label>Password:
        <input type="password" name="password">
    </label>
    <select name="role">
        <option value="BUYER">Buyer</option>
        <option value="SELLER">Seller</option>
    </select>
    <input type="submit" value="Register">
</form>
</body>
</html>