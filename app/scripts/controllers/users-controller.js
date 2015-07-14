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
      $location.path('/user/' + user.id + '/edit');
    };

  })
  .controller('UserViewCtrl', function($scope, $rootScope, $location, $routeParams, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.user=Users.get({id:$routeParams.id});
    $scope.canEdit = true;
    $scope.canDelete = true;

    $scope.deleteUser=function(user){
      user.$delete(function(){
          $location.path('/user');
      });
    };
    $scope.editUser=function(user){
      $location.path('/user/' + user.id + '/edit');
    };

  })
  .controller('UserEditCtrl', function($scope, $rootScope, $location, $routeParams, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.user=Users.get({id:$routeParams.id});
    $scope.canEdit = false;
    $scope.canDelete = true;

    $scope.deleteUser=function(user){
      person.$delete(function(){
          $location.path('/user');
      });
    };
    $scope.updateUser=function(){
      $scope.user.$update(function(){
          $location.path('/user/'+$scope.user.id);
      });
    };
  })
  .controller('UserNewCtrl', function($scope, $rootScope, $location, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.user = new Users();
    $scope.user.date = Date.now();
    $scope.canEdit = false;
    $scope.canDelete = false;

    $scope.addUser=function(){
      $scope.user.$save(function(){
        $location.path('/user');
      });
    };
  });
