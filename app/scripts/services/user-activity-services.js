'use strict';

angular.module('de1mos.CoffeeProc')
  .factory('UserActivities', function($resource){
    return $resource('api/user-activities/:id', { id: '@id'}, {
      update: {
        method: 'PUT'
      }
    });
  });