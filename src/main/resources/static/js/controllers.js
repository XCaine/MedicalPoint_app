// CONTROLERS

//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////main home page controller///////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
medicalApp.controller('homeController',['$scope','$http', function($scope,$http){

}]);
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////medical points that can be viewed////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
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
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////panel for data insert///////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
medicalApp.controller('datainsertController',['$scope','$http', function($scope,$http){

    $scope.jsonrequest='';
    
    $scope.hospital = {
        "name": "Poradnia leczenia zaburzeń snu",
        "city": "sochaczewski",
        "province": "mazowieckie",
        "country": "Poland",
        
        "address":{
            "streetName": "Marsz. Piłsudskiego",
            "streetNumber": "65",
            "postalCode": "96-500"
        },
        "coordinates":{
            "latitude":"",
            "longitude":""
        }
        
    };
    
    $scope.googleName='';
    $scope.results='';
    
    $scope.flag=false;
    $scope.changeflag= function() {
        $scope.flag==true ? $scope.flag=false : $scope.flag=true; 
    }
    
    $scope.request = function () {
    $http.get("http://localhost:8080/admin/medicalpoint/addwithname/"+$scope.googleName)
        .success(function(result) { 
            $scope.results=result;
            $scope.googleName='';
        });
    }
    
    
    $scope.submit = function () {
        $http.get("https://maps.googleapis.com/maps/api/geocode/json?address="+$scope.hospital.city+"+"+$scope.hospital.address.streetName+"+"+$scope.hospital.address.streetNumber+"+"+"&key=AIzaSyCPoLpNU2TEKnVNQOrrJH8SlUqdv-93LN0")
        .success(function(result) { 
            $scope.results=result;
            $scope.hospital.coordinates.latitude=$scope.results.results[0].geometry.location.lat;
            $scope.hospital.coordinates.longitude=$scope.results.results[0].geometry.location.lng;
            
            $scope.jsonrequest=JSON.stringify($scope.hospital);
            $scope.hospital.name='';
            $scope.hospital.city='';
            $scope.hospital.province='';
            $scope.hospital.country='';
            $scope.hospital.address.streetName='';
            $scope.hospital.address.streetNumber='';
            $scope.hospital.address.postalCode='';
            
        $http.get("http://localhost:8080/admin/medicalpoint/addwithJSON", {
            params: {
                'json' : $scope.jsonrequest
            }
        })
        .success(function(result) {
            $scope.results=result;
        }); 
    })}
}]);  


//////////////////////////////////////////////////////////////////////////////////////
////////////////////////page where a user can get help/////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
medicalApp.controller('gethelpController',['$scope','$http', function($scope,$http){

    $scope.city='Warszawa';
    $scope.street='Nowowiejska';
    $scope.number='15/19';
    $scope.illness = {
    singleSelect: null,
    option1: 'Ankle sprain',
    option2: 'Meniscus Tear',
    option3: 'Shoulder Dislocation',
    option4: 'Hand fracture',
    option5: 'Leg fracture',
    option6: 'Ear pain',
    option7: 'Eye pain',
    option8: 'Blurry vision',
    option9: 'Itchy eyes',
    option10: 'Swollen eyelids',
    option11: 'Acute abdomen',
    option12: 'Chest pain',
    option13: 'Fainting',
    option14: 'Breathing difficulty',
    option15: 'Coughing up',
    option16: 'Vomiting',
    option17: 'Bleeding',
    option18: 'Crabs',
    option19: 'Herpes'
   };
    
    
    $scope.cityBySpecialty='Warszawa';
    $scope.streetBySpecialty='Nowowiejska';
    $scope.numberBySpecialty='15/19';
    $scope.specialty = {
    singleSelect: null,
    option1: 'Dermatology and Venereology',
    option2: 'Internal Medicine',
    option3: 'Orthopaedics',
    option4: 'Paediatrics',
    option5: 'Public Health Medicine',
    option6: 'Radiology',
    option7: 'Urology'
   };
    
    
    $scope.results='';
    $scope.resultsByIllness='';
    $scope.resultsBySpecialty='';
    $scope.object='';
    $scope.warning1='';
    $scope.warning2='';
    
    
    $scope.requestByIllness = function () {
        
        $scope.warning1='';
        $scope.warning2='';
        if($scope.illness.singleSelect===null){
            $scope.warning1='please select illness!';
            return;
        }   
            
    $http.get("https://maps.googleapis.com/maps/api/geocode/json?address="+$scope.city+"+"+$scope.street+"+"+$scope.number+"+"+"&key=AIzaSyCPoLpNU2TEKnVNQOrrJH8SlUqdv-93LN0")
        .success(function(result) { 
            $scope.results=result;

            for(var i=0; i<$scope.results.results[0].address_components.length; i++){
                if($scope.results.results[0].address_components[i].types[0]=="administrative_area_level_1")
                    $scope.province=$scope.results.results[0].address_components[i].long_name;
            }
            for(var j=0; j<$scope.results.results[0].address_components.length; j++){
                if($scope.results.results[0].address_components[j].types[0]=="locality")
                    $scope.city=$scope.results.results[0].address_components[j].long_name;
            }

            $http.get("http://localhost:8080/findmedicalpoint/findmedicalpoint/", {
            params: {
                    lat: $scope.results.results[0].geometry.location.lat,
                    lon: $scope.results.results[0].geometry.location.lng,
                    illness: $scope.illness.singleSelect,
                    city: $scope.city,
                    province: $scope.province

                    }
                })
        
                .success(function(result) {
                    if(result.results==null){
                        console.log("brak wyników");
                        return;
                    } 
                    $scope.resultsByIllness=result;
                });   
            });    
        }
    
    
    $scope.requestBySpecialty = function () {
        
        $scope.warning1='';
        $scope.warning2='';
        if($scope.specialty.singleSelect===null){
            $scope.warning2='please select specialty!';
            return;
        }   
            
    $http.get("https://maps.googleapis.com/maps/api/geocode/json?address="+$scope.city+"+"+$scope.street+"+"+$scope.number+"+"+"&key=AIzaSyCPoLpNU2TEKnVNQOrrJH8SlUqdv-93LN0")
        .success(function(result) { 
            $scope.results=result;

            for(var i=0; i<$scope.results.results[0].address_components.length; i++){
                if($scope.results.results[0].address_components[i].types[0]=="administrative_area_level_1")
                    $scope.province=$scope.results.results[0].address_components[i].long_name;
            }
            for(var j=0; j<$scope.results.results[0].address_components.length; j++){
                if($scope.results.results[0].address_components[j].types[0]=="locality")
                    $scope.city=$scope.results.results[0].address_components[j].long_name;
            }

            $http.get("http://localhost:8080/findmedicalpoint/findmedicalpoint2/", {
            params: {
                    lat: $scope.results.results[0].geometry.location.lat,
                    lon: $scope.results.results[0].geometry.location.lng,
                    specialty: $scope.specialty.singleSelect,
                    city: $scope.city,
                    province: $scope.province

                    }
                })
        
                .success(function(result) {
                    if(result.results==null){
                        console.log("brak wyników");
                        return;
                    } 
                    $scope.resultsBySpecialty=result;
                });   
            });    
        }
    
        
}]);
//////////////////////////////////////////////////////////////////////////////////////
////////////////////////information about the app/////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
medicalApp.controller('appinfoController',['$scope', function($scope){

}]);











































