<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Products</title>
</head>
<script>
    /* function add_to_cart() {
         $(".button").click(function () {
             buttonClick($(this).val());

         })
     }

     function buttonClick(x) {
         $.ajax({
             type: "GET",
             url: "addProduct?id" + x,

             success: function (response) {
                 alert('Success!' + ' Response: ' + response);
             }
         });
     }*/
</script>
<body>
<#list products as product>
    <div id="${product.id}">
        <h4>${product.name} ${product.price}</h4>
        <a href="/addProduct?id=${product.id}">Add to cart</a>
        <br>
    </div>
</#list>
<br>
<a href="/makeOrder">Make Order</a>
</body>
</html>