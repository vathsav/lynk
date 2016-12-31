// Config
var Particle = require('particle-api-js');
var particle = new Particle();
var ruthlessDynamiteId = '55ff6d066678505551361367';
var digitalWrite = 'digitalWrite';

// Login
particle.login({username: 'email@live.in', password: 'password'}).then(
		function(data) {
			console.log('API call completed on promise resolve: ', data.body.access_token);
			particle.callFunction({ deviceId: ruthlessDynamiteId, name: digitalWrite, argument: 'D4:1', auth: data.body.access_token });
		},
		function(err) {
			console.log('API call completed on promise fail: ', err);
		}
	);