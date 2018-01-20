// CONTROLERS

//main home page controller
medicalApp.controller('homeController',['$scope','$http', function($scope,$http){

}]);
//medical points that can be viewed
medicalApp.controller('medicalpointsController',['$scope','$http', function($scope,$http){
        
    $scope.results='';
    $scope.ID='73';
    
    $scope.request = function () {    
    $http.get("http://localhost:8080/medicalpoint/searchbyid/"+$scope.ID)
        .success(function(result) { 
            $scope.results=result; 
            $scope.ID='';
        });       
    }
}]);
//panel for data insert
medicalApp.controller('datainsertController',['$scope','$http', function($scope,$http){

    $scope.jsonrequest='';
    $scope.hospital = {
        "name": "Szpital Psychiatryczny 'Elka' ",
        "city": "Warszawa",
        "province": "mazowieckie",
        "country": "Polska",
        
        "address":{
            "street_name": "Nowowiejska",
            "street_number": "15/19",
            "postal_code": "00-665"
        }
    };
    
    $scope.name='';
    $scope.results='';
    
    $scope.request = function () {
    $http.get("http://localhost:8080/admin/medicalpoint/addwithname/"+$scope.name)
        .success(function(result) { 
            $scope.results=result;
            $scope.name='';
        });                  
    }
    
    $scope.submit = function () {

        $scope.jsonrequest=JSON.stringify($scope.hospital);
        
        $scope.hospital.name='';
        $scope.hospital.city='';
        $scope.hospital.province='';
        $scope.hospital.country='';
        $scope.hospital.street_name='';
        $scope.hospital.street_number='';
        $scope.hospital.postal_code='';
        
        $http.post("http://localhost:8080/admin/medicalpoint/addwithJSON", $scope.jsonrequest);
    }

}]);
//page when a user can get help
medicalApp.controller('gethelpController',['$scope','$http', function($scope,$http){

    $scope.city='Warszawa';
    $scope.street='Nowowiejska';
    $scope.number='15/19';
    $scope.illness='Eye pain';
    $scope.results='';
    $scope.finalresults='';
    
    $scope.request = function () {
    $http.get("https://maps.googleapis.com/maps/api/geocode/json?address="+$scope.city+"+"+$scope.street+"+"+$scope.number+"+"+"&key=AIzaSyCPoLpNU2TEKnVNQOrrJH8SlUqdv-93LN0")
        .success(function(result) { 
            $scope.results=result;
        
            $http.get("http://localhost:8080/findmedicalpoint/findmedicalpoint/", {
            params: {
//            lat:'52.2192637',
//            lon:'21.0129685',
//            illness:'Eye pain',
//            city:'Warszawa',
//            province:'mazowieckie'
                    lat: $scope.results.results[0].geometry.location.lat,
                    lon: $scope.results.results[0].geometry.location.lng,
                    illness: $scope.illness,
                    city: $scope.results.results[0].address_components[3].long_name,
                    province: $scope.results.results[0].address_components[5].long_name
                    }
                })
        
                .success(function(result) {
                    $scope.finalresults=result;
                });   
            });    
        }
    
        
}]);
//information about the app
medicalApp.controller('appinfoController',['$scope', function($scope){

}]);
