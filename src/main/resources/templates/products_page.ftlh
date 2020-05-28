<!doctype html>
<html lang="en" ng-app="app">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Products</title>
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.7.9/angular.min.js"></script>
<script src="js/shop.js"></script>
<body>
<div>
    <table>
        <tr>
            <td>Product name</td>
            <td>Price</td>
            <td>Category</td>
        </tr>
        <#list products as product>
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.category}</td>
                <td>
                    <button add-product id='${product.id}'>Add to cart</button>
                </td>
            </tr>
        </#list>
    </table>
</div>
<br>
<a href="/makeOrder">Make Order</a>
</body>
</html>