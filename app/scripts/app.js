'use strict';

angular.module('de1mos.CoffeeProc', ['ngAnimate', 'ngRoute','ngResource'])

  .config(function($locationProvider, $routeProvider) {

    $locationProvider.html5Mode(true);

    $routeProvider
      .when('/', {
        templateUrl: 'views/home.html',
        controller: 'MainCtrl'
      })
      .when('/users', {
        templateUrl: 'views/user/users-list.html',
        controller: 'UsersCtrl'
      })
      .when('/user/new', {
        templateUrl: 'views/user/user-new.html',
        controller: 'UserNewCtrl'
      })
      .when('/user/:id', {
        templateUrl: 'views/user/user-view.html',
        controller: 'UserViewCtrl'
      })
      .when('/user/:id/edit', {
        templateUrl: 'views/user/user-edit.html',
        controller: 'UserEditCtrl'
      })
      .when('/purchases', {
        templateUrl: 'views/purchase/purchases-list.html',
        controller: 'PurchasesCtrl'
      })
      .when('/purchases/new', {
        templateUrl: 'views/purchase/purchase-new.html',
        controller: 'PurchasesNewCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });

  });