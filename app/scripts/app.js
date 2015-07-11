'use strict';

angular.module('de1mos.CoffeeProc', ['ngAnimate', 'ngRoute','ngResource'])

  .config(function($locationProvider, $routeProvider) {

    $locationProvider.html5Mode(false);

    $routeProvider
      .when('/', {
        templateUrl: 'views/home.html',
        controller: 'MainCtrl'
      })
      .when('/user', {
        templateUrl: 'views/user.html',
        controller: 'UserCtrl'
      })
      .when('/user/new', {
        templateUrl: 'views/user-new.html',
        controller: 'UserNewCtrl'
      })
      .when('/user/:id', {
        templateUrl: 'views/user-view.html',
        controller: 'UserViewCtrl'
      })
      .when('/user/:id/edit', {
        templateUrl: 'views/user-edit.html',
        controller: 'UserEditCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });

  });