// Config
var Particle = require('particle-api-js');
var particle = new Particle();
var ruthlessDynamiteId = '55ff6d066678505551361367';
var digitalWrite = 'digitalWrite';

// Login
particle.login({username: 'vathsav@live.in', password: 'password'}).then(
		function(data) {
			console.log('API call completed on promise resolve: ', data.body.access_token);
			particle.callFunction({ deviceId: ruthlessDynamiteId, name: digitalWrite, argument: 'D0:0', auth: data.body.access_token });
			particle.callFunction({ deviceId: ruthlessDynamiteId, name: digitalWrite, argument: 'D3:0', auth: data.body.access_token });
			particle.callFunction({ deviceId: ruthlessDynamiteId, name: digitalWrite, argument: 'D5:0', auth: data.body.access_token });
			particle.callFunction({ deviceId: ruthlessDynamiteId, name: digitalWrite, argument: 'D7:0', auth: data.body.access_token });
			// Light D1 and Fan D5
		},
		function(err) {
			console.log('API call completed on promise fail: ', err);
		}
	);