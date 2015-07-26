'use strict';

angular.module('de1mos.CoffeeProc', ['ngAnimate', 'ngRoute','ngResource', 'ui.bootstrap'])

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
      .when('/users/new', {
        templateUrl: 'views/user/user-new.html',
        controller: 'UserNewCtrl'
      })
      .when('/users/:id', {
        templateUrl: 'views/user/user-view.html',
        controller: 'UserViewCtrl'
      })
      .when('/users/:id/edit', {
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
      .when('/user-activities/:userId/new', {
        templateUrl: 'views/user-activity/user-activity-new.html',
        controller: 'UserActivitiesNewCtrl'
      })
      .when('/user-activities/:userId', {
        templateUrl: 'views/user-activity/user-activity-list.html',
        controller: 'UserActivitiesCtrl'
      })
      .when('/user-activities/:userId/edit/:activityId', {
        templateUrl: 'views/user-activity/user-activity-edit.html',
        controller: 'UserActivityEditCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });

  });