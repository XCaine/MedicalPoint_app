// ROUTES
medicalApp.config(['$routeProvider', function ($routeProvider){
    
    $routeProvider
    .when('/', {
        templateUrl: 'pages/home.html',
        controller: 'homeController'
    })
    .when('/gethelp', {
        templateUrl: 'pages/gethelp.html',
        controller: 'gethelpController'
    })
    .when('/datainsert', {
        templateUrl: 'pages/datainsert.html',
        controller: 'datainsertController'
    })
    .when('/medicalpoints', {
        templateUrl: 'pages/medicalpoints.html',
        controller: 'medicalpointsController'
    })
    .when('/appinfo', {
        templateUrl: 'pages/appinfo.html',
        controller: 'appinfoController'
    })

    .otherwise({ redirectTo: '/'});
    
}]);
