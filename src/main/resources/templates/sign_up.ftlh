<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<script>function checkPassword() {
        let password = document.getElementById('password').value;
        let passwordLengthError = document.getElementById('passwordLengthError');
        let passwordContainUpperCaseLetterError = document.getElementById('passwordContainUpperCaseLetterError');
        let passwordContainNumberError = document.getElementById('passwordContainNumberError');
        let confirmPassword = document.getElementById('confirmPassword');
        var registerButton = document.getElementById('registerButton');
        registerButton.disabled = !(password === confirmPassword);
        let cond1 = false;
        let cond2 = false;
        let cond3 = false;
        console.log(password);
        if (password.length < 8) {
            passwordLengthError.style.color = "red";
            cond1 = false;
        } else {
            passwordLengthError.style.color = "green";
            cond1 = true;
        }
        if (password.toString().search(/[A-ZА-Я]/) === -1) {
            passwordContainUpperCaseLetterError.style.color = "red";
            cond2 = false;
        } else {
            passwordContainUpperCaseLetterError.style.color = "green";
            cond2 = true;
        }
        if (password.toString().search(/\d/) === -1) {
            passwordContainNumberError.style.color = "red";
            cond3 = false;
        } else {
            passwordContainNumberError.style.color = "green";
            cond3 = true;
        }
        confirmPassword.disabled = !(cond1 && cond2 && cond3);
    }

    function checkConfirmPassword() {
        var password = document.getElementById('password').value;
        var confirmPassword = document.getElementById('confirmPassword').value;
        var registerButton = document.getElementById('registerButton');
        registerButton.disabled = !(password === confirmPassword);

    }</script>
<body>
<form action="/signUp" method="post"onload="checkPassword()">
    <label>Login:
        <input type="text" name="login">
    </label><br>
    <label>Password:
        <input id="password" type="password" name="password" onchange="checkPassword()">
    </label><br>
    <select name="role">
        <option value="BUYER">Buyer</option>
        <option value="SELLER">Seller</option>
    </select><br>
    <h5 class="card-body__text-info">
        Your password should meet the following requirements:
        <ul>
            <li id="passwordLengthError">
                Password should be at least 8 characters long
            </li>
            <li id="passwordContainUpperCaseLetterError">
                Password should contain at least 1 uppercase letter
            </li>
            <li id="passwordContainNumberError">
                Password should contain at least 1 number
            </li>
        </ul>
    </h5>
    <br>
    <label for="confirmPassword"> Confirm : <input type="password" id="confirmPassword" onload="checkConfirmPassword()" onchange="checkConfirmPassword()"></label><br>
    <input id="registerButton" type="submit" value="Register">
</form>
</body>
</html>