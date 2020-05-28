var app = angular.module('myApp', []);
app.controller('ProductController', function ($scope, $http) {
    $http.get('admin/getCategories')
        .then(function (result) {
            console.log(result);
            $scope.categories = result.data;
        }, function (response) {
            console.log(response);
        });
    $scope.createProduct = function () {
        let params = {
            name: $scope.form.$$element[0][0].value,
            price: $scope.form.$$element[0][1].value,
            category: $scope.form.$$element[0][2].value
        }
        $http.post('/admin/createProduct', params).then(function () {
            $scope.form.$$element[0][0].value = '';
            $scope.form.$$element[0][1].value = '';
        }, function () {
            alert("ошибка, хз чё случилосс");
        })
    }
});