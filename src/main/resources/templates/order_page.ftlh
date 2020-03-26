<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Order Page</title>
</head>
<body>
<#if order??>
    <h1>Order №${order.id}</h1><br>
    <h2>User : ${order.user.login}</h2>
    <#list order.ordered_products as product>
        ${product.id}. ${product.name}  price: ${product.price}<br>
    </#list>
<#else>
    <h1>Product with such id not found</h1>
</#if>
</body>
</html>