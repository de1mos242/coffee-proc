'use strict';

angular.module('de1mos.CoffeeProc')
  .factory('Users', function($resource){
    return $resource('api/user/:id', { id: '@id'}, {
      update: {
        method: 'PUT'
      }
    });
  });