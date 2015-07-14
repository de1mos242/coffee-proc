'use strict';

angular.module('de1mos.CoffeeProc')

  .controller('MainCtrl', function($rootScope, $location) {
    $rootScope.$path = $location.path.bind($location);
  })

