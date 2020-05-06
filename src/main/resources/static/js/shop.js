var app = angular.module('app', []);

app.directive('addProduct', function ($http) {
    return {
        link: function (scope, element, attrs) {
            element.on('click', function () {
                console.log(attrs.id)
                $http.get('http://localhost/addProduct?id=' + attrs.id)
                    .then(function (result) {
                        console.log(result)
                        if (result.data === "") {
                            element[0].innerHTML = 'Add to cart'
                        } else {
                            element[0].innerHTML = 'Remove'
                            console.log(element)
                        }
                    }, function (result) {
                        console.log(result)
                    })
            })
        }
    }
});