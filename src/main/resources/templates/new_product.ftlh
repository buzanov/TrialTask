<!doctype html>
<html lang="en" ng-app="myApp" ng-controller="ProductController">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add product</title>
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.7.9/angular.min.js"></script>
<script src="js/product.js"></script>
<body>
<form name="form" action="/createProduct" method="post">
    <input type="text" name="name" placeholder="name">
    <input type="text" name="price" placeholder="price">
    <select name="category">
        <option value="{{category}}" ng-repeat="category in categories">{{category}}</option>
    </select>
    <br>
    <input type="button" value="Create" ng-click="createProduct()">
</form>
</body>
</html>