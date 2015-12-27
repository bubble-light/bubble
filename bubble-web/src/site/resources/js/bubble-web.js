/**
 * Copyright [2015-2017] [https://github.com/bubble-light/]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

Bubble = {};

Bubble.extend = function(a, b) {
	var n;
	if (!a) {
		a = {};
	}
	for (n in b) {
		a[n] = b[n];
	}
	return a;
};

(function(_bubble) {
	_bubble.extend(_bubble, {
		init : function(_url) {
			this.initParam(_url);
		},
		initParam : function(_url) {
			this.protocol = _bubble.protocol;
			this.host = _bubble.host;
			this.port = _bubble.port;
			this.url = _url;
			this.ajaxPath = this.protocol + '://' + this.host + ':' + this.port
					+ '/bubble-application/' + _url;
		},
		loadData : function(param, s, e) {
			$.ajax({
				type : 'POST',
				dataType : 'jsonp',
				url : this.ajaxPath,
				jsonp : 'jsonpcallback',
				jsonpCallback : "success_jsonpCallback",
				processData : false,
				data : param,
				success : function(jsonData) {
					var _data = eval(jsonData);
					s(_data);
				},
				error : e || function(a, b, c) {
					if (Bubble.debugModel) {
						alert('Error : ' + a + b + c);
					} else {
						alert('Load data error :' + a);
					}

				}
			});
		}
	});
})(Bubble);
