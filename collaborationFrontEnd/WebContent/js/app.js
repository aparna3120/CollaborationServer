// create the module and name it myApp
// also include ngRoute for all our routing needs
var App = angular.module('myApp', ['ngRoute']);

//configuring routes
App.config(function($routeProvider)
{
	 		 $routeProvider
		         // route for the index page
		         .when('/', {
		                 templateUrl : 'index.html',
		                 controller  : 'mainCtrl'
		         })	
		         
		         $routeProvider
		         // route for the contact us page
		         .when('/contactus', {
		                 templateUrl : 'ContactUs.html',
		                 controller  : 'contactusCtrl'
		         })	
		         
		         $routeProvider
		         // route for the about us page
		         .when('/aboutus', {
		                 templateUrl : 'AboutUs.html',
		                 controller  : 'aboutusCtrl'
		         })	
		         
		         
		         $routeProvider
		         // route for the about us page
		         .when('/register', {
		                 templateUrl : 'Register.html',
		                 
		         })	

		         
		           $routeProvider
		         // route for the about us page
		         .when('/login', {
		                 templateUrl : 'Login.html',
		                
		         })
		         
		         
});

//create the controller and inject Angular's $scope
myApp.controller('mainCtrl', function($scope) {
        // create a message to display in our view
        $scope.heading = 'Welcome to get2gether.com';
        $scope.message = 'Here you will find the meaning of life.';
});


myApp.controller('contactusCtrl', function($scope) {
    // create a message to display in our view
    $scope.heading = 'Welcome to ContactUs';
    $scope.message = 'Here you will find the meaning of life.';
});


myApp.controller('aboutusCtrl', function($scope) {
    // create a message to display in our view
    $scope.heading = 'Welcome to ContactUs';
    $scope.message = 'Here you will find the meaning of life.';
});


/*'use strict';

var App = angular.module("myApp", ["ngRoute"]);

App.config(function($routeProvider,$locationProvider) {
    $routeProvider
    .when("#/", {
        templateUrl : "index.html"
    })
    .when("/login", {
        templateUrl : "login.html"        
    })
    .when("/about", {
        templateUrl : "about.html"
    })
     .when("/contact", {
        templateUrl : "contact.html"
    })
    .when("/userreg", {
        templateUrl : "userreg.html"        
    })
    .when("/userhome", {
        templateUrl : "userhome.html"        
    });
    $locationProvider.html5Mode({
    	 	  requireBase: false
    	});
});*/