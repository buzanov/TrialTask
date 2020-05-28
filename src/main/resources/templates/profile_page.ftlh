<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body>
<#if user??>
    <h1>${user.login}`s page</h1>
    <h2>Orders history</h2>
    <#list orders as order>
        <a href="/order/${order.id}">Order ${order.id}</a>
    </#list>
<#else>
</#if>
</body>
</html>