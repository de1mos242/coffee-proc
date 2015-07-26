'use strict';

angular.module('de1mos.CoffeeProc')

  .controller('PurchasesCtrl', function($scope, $rootScope, $location, $route, Purchases) {
    $rootScope.$path = $location.path.bind($location);
    $scope.purchases = Purchases.query();
    $scope.canEdit = true;
    $scope.canDelete = true;

    $scope.deletePurchase=function(purchase){
      purchase.$delete(function(){
        $route.reload();
      });
    };
    $scope.editPurchase=function(purchase){
      $location.path('/purchases/' + purchase.id + '/edit');
    };

  })

  .controller('PurchasesNewCtrl', function($scope, $rootScope, $location, $route, Purchases, Users) {
    $rootScope.$path = $location.path.bind($location);
    $scope.purchase = new Purchases();
    $scope.purchase.date = new Date();
    $scope.canEdit = false;
    $scope.canDelete = false;
    $scope.users = Users.query()

    $scope.addPurchase=function(){
      $scope.purchase.$save(function(){
        $location.path('/purchases');
      });
    };
  })
