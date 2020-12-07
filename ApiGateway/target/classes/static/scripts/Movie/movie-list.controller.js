'use strict';

angular.module('movieList')
    .controller('MovieListController', ['$http', function ($http) {
        var self = this;

       $http.get('api/movie/movies').then(function (resp) {
            self.vetList = resp.data;
        });
    }]);

function getURLParameter(name) {
    return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]);
}
function hideURLParams() {
    //Parameters to hide (ie ?success=value, ?error=value, etc)
    var hide = ['success','error'];
    for(var h in hide) {
        if(getURLParameter(h)) {
            history.replaceState(null, document.getElementsByTagName("title")[0].innerHTML, window.location.pathname);
        }
    }
}