'use strict';

angular.module('de1mos.CoffeeProc')

  .controller('UserActivitiesCtrl', function($scope, $rootScope, $location, $route, $routeParams, UserActivities, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.user = Users.get({id:$routeParams.userId})
    $scope.userActivities = UserActivities.query({userId : $routeParams.userId});
  })

  .controller("UserActivitiesNewCtrl", function($scope, $rootScope, $location, $route, $routeParams, UserActivities, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.activity = new UserActivities();
    $scope.activity.user=Users.get({id:$routeParams.userId});
    $scope.activity.activityStart = new Date();
    $scope.activity.activityFinish = new Date();

    $scope.addActivity=function(){
      $scope.activity.$save(function(){
        $location.path('/user-activities/'+$routeParams.userId);
      });
    };
  })

