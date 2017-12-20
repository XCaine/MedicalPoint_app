// CONTROLERS

//main home page controller
medicalApp.controller('homeController',['$scope','$http', function($scope,$http){

}]);
//medical points that can be viewed
medicalApp.controller('medicalpointsController',['$scope','$http', function($scope,$http){
        
    $scope.results='';
    $scope.ID='20';
    
    $scope.request = function () {    
    $http.get("http://localhost:8080/medicalpoint/searchbyid/"+$scope.ID)
        .success(function(result) { 
            $scope.results=result; 
        });       
    }
}]);
//panel for data insert
medicalApp.controller('datainsertController',['$scope','$http', function($scope,$http){
    
    $scope.name='';
    $scope.results='';
    
    $scope.request = function () {
    $http.get("http://localhost:8080/medicalpoint/addbyname/"+$scope.name)
        .success(function(result) { 
            $scope.results=result; 
        });                  
    }

}]);
//page when a user can get help
medicalApp.controller('gethelpController',['$scope','$http', function($scope,$http){

    $scope.city='warszawa';
    $scope.street='nowowiejska';
    $scope.number='15/19';
    $scope.results='';
    
    $scope.request = function () {
    $http.get("https://maps.googleapis.com/maps/api/geocode/json?address="+$scope.city+"+"+$scope.street+"+"+$scope.number+"+"+"&key=AIzaSyCPoLpNU2TEKnVNQOrrJH8SlUqdv-93LN0")
        .success(function(result) { 
            $scope.results=result; 
        });                  
    }
        
}]);
//information about the app
medicalApp.controller('appinfoController',['$scope', function($scope){

}]);
