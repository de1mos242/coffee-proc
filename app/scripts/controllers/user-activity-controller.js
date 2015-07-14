'use strict';

angular.module('de1mos.CoffeeProc')

  .controller('UserActivitiesCtrl', function($scope, $rootScope, $location, $route, $routeParams, UserActivities, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.user = Users.get({id:$routeParams.userId})
    $scope.userActivities = UserActivities.query({userId : $routeParams.userId});
  })

