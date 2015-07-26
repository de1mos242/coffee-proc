'use strict';

angular.module('de1mos.CoffeeProc')

  .controller('UsersCtrl', function($scope, $rootScope, $location, $route, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.users = Users.query();
    $scope.canEdit = true;
    $scope.canDelete = true;

    $scope.deleteUser=function(user){
      user.$delete(function(){
        $route.reload();
      });
    };
    $scope.editUser=function(user){
      $location.path($scope.baseUrl + '/users/' + user.id + '/edit');
    };

  })
  .controller('UserViewCtrl', function($scope, $rootScope, $location, $routeParams, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.user=Users.get({id:$routeParams.id});
    $scope.canEdit = true;
    $scope.canDelete = true;

    $scope.deleteUser=function(user){
      user.$delete(function(){
          $location.path('/users');
      });
    };
    $scope.editUser=function(user){
      $location.path($scope.baseUrl + '/users/' + user.id + '/edit');
    };

  })
  .controller('UserEditCtrl', function($scope, $rootScope, $location, $routeParams, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.user=Users.get({id:$routeParams.id});

    $scope.updateUser=function(){
      $scope.user.$update(function(){
        console.log("we are going to " + '/users/'+$scope.user.id)
          $location.path('/users/'+$scope.user.id);
      });
    };
  })
  .controller('UserNewCtrl', function($scope, $rootScope, $location, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.user = new Users();
    $scope.user.firstName = "";
    $scope.user.lastName = "";

    $scope.addUser=function(){
      $scope.user.$save(function(){
        $location.path('users');
      });
    };
  });
