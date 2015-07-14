'use strict';

angular.module('de1mos.CoffeeProc')
  .factory('Purchases', function($resource){
    return $resource('api/purchase/:id', { id: '@id'}, {
      update: {
        method: 'PUT'
      }
    });
  });