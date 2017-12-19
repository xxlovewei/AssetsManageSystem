/**
 * INSPINIA - Responsive Admin Theme
 * 
 * Main directives.js file Define directives for used plugin
 * 
 * 
 * Functions (directives) - sideNavigation - iboxTools - minimalizaSidebar -
 * vectorMap - sparkline - icheck - ionRangeSlider - dropZone - responsiveVideo -
 * chatSlimScroll - customValid - fullScroll - closeOffCanvas - clockPicker -
 * landingScrollspy - fitHeight - iboxToolsFullScreen - slimScroll - truncate -
 * touchSpin - markdownEditor - resizeable - bootstrapTagsinput - passwordMeter
 * 
 */

function getUuid() {
	var len = 32;// 32长度
	var radix = 16;// 16进制
	var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
			.split('');
	var uuid = [], i;
	radix = radix || chars.length;
	if (len) {
		for (i = 0; i < len; i++)
			uuid[i] = chars[0 | Math.random() * radix];
	} else {
		var r;
		uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
		uuid[14] = '4';
		for (i = 0; i < 36; i++) {
			if (!uuid[i]) {
				r = 0 | Math.random() * 16;
				uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
			}
		}
	}
	return uuid.join('');
}

/**
 * pageTitle - Directive for set Page title - mata title
 */
function pageTitle($rootScope, $timeout, $transitions) {
	return {
		link : function(scope, element) {
			$transitions.onSuccess({
				to : '**'
			}, function(trans) {
				var current = trans.router.globals.current;
				var title = "后台管理";
				if (angular.isDefined(current.data)
						&& angular.isDefined(current.data.pageTitle)) {
					title = current.data.pageTitle;
				}
				element.text(title);
			});
		}
	}
};

/**
 * sideNavigation - Directive for run metsiMenu on sidebar navigation
 */
function sideNavigation($timeout) {
	return {
		restrict : 'A',
		link : function(scope, element) {
			// Call the metsiMenu plugin and plug it to sidebar navigation
			$timeout(function() {
				element.metisMenu();
			});

			// Colapse menu in mobile mode after click on element
			var menuElement = $('#side-menu a:not([href$="\\#"])');
			menuElement.click(function() {
				if ($(window).width() < 769) {
					$("body").toggleClass("mini-navbar");
				}
			});

			// Enable initial fixed sidebar
			if ($("body").hasClass('fixed-sidebar')) {
				var sidebar = element.parent();
				sidebar.slimScroll({
					height : '100%',
					railOpacity : 0.9,
				});
			}

			// Enable initial fixed sidebar
			$(".navbar-static-top").removeClass('navbar-static-top').addClass(
					'navbar-fixed-top');
			// $("body").removeClass('boxed-layout');
			// $("body").addClass('fixed-nav');

		}
	};
};

/**
 * responsibleVideo - Directive for responsive video
 */
function responsiveVideo() {
	return {
		restrict : 'A',
		link : function(scope, element) {
			var figure = element;
			var video = element.children();
			video.attr('data-aspectRatio', video.height() / video.width())
					.removeAttr('height').removeAttr('width')

			// We can use $watch on $window.innerWidth also.
			$(window).resize(
					function() {
						var newWidth = figure.width();
						video.width(newWidth).height(
								newWidth * video.attr('data-aspectRatio'));
					}).resize();
		}
	}
}

/**
 * iboxTools - Directive for iBox tools elements in right corner of ibox
 */
function iboxTools($timeout) {
	return {
		restrict : 'A',
		scope : true,
		templateUrl : 'views/common/ibox_tools.html',
		controller : function($scope, $element) {
			// Function for collapse ibox
			$scope.showhide = function() {
				var ibox = $element.closest('div.ibox');
				var icon = $element.find('i:first');
				var content = ibox.children('.ibox-content');
				content.slideToggle(200);
				// Toggle icon from up to down
				icon.toggleClass('fa-chevron-up')
						.toggleClass('fa-chevron-down');
				ibox.toggleClass('').toggleClass('border-bottom');
				$timeout(function() {
					ibox.resize();
					ibox.find('[id^=map-]').resize();
				}, 50);
			};
			// Function for close ibox
			$scope.closebox = function() {
				var ibox = $element.closest('div.ibox');
				ibox.remove();
			}
		}
	};
}

/**
 * iboxTools with full screen - Directive for iBox tools elements in right
 * corner of ibox with full screen option
 */
function iboxToolsFullScreen($timeout) {
	return {
		restrict : 'A',
		scope : true,
		templateUrl : 'views/common/ibox_tools_full_screen.html',
		controller : function($scope, $element) {
			// Function for collapse ibox
			$scope.showhide = function() {
				var ibox = $element.closest('div.ibox');
				var icon = $element.find('i:first');
				var content = ibox.children('.ibox-content');
				content.slideToggle(200);
				// Toggle icon from up to down
				icon.toggleClass('fa-chevron-up')
						.toggleClass('fa-chevron-down');
				ibox.toggleClass('').toggleClass('border-bottom');
				$timeout(function() {
					ibox.resize();
					ibox.find('[id^=map-]').resize();
				}, 50);
			};
			// Function for close ibox
			$scope.closebox = function() {
				var ibox = $element.closest('div.ibox');
				ibox.remove();
			};
			// Function for full screen
			$scope.fullscreen = function() {
				var ibox = $element.closest('div.ibox');
				var button = $element.find('i.fa-expand');
				$('body').toggleClass('fullscreen-ibox-mode');
				button.toggleClass('fa-expand').toggleClass('fa-compress');
				ibox.toggleClass('fullscreen');
				setTimeout(function() {
					$(window).trigger('resize');
				}, 100);
			}
		}
	};
}

/**
 * minimalizaSidebar - Directive for minimalize sidebar
 */
function minimalizaSidebar($timeout) {
	return {
		restrict : 'A',
		template : '<a  class="navbar-minimalize minimalize-styl-2 btn btn-switch" href="" ng-click="minimalize()"><i class="fa fa-bars"></i></a>',
		controller : function($scope, $element) {
			$scope.minimalize = function() {
				$("body").toggleClass("mini-navbar");

				if (!$('body').hasClass('mini-navbar')
						|| $('body').hasClass('body-small')) {
					// Hide menu in order to smoothly turn on when maximize menu
					$('#side-menu').hide();
					// For smoothly turn on menu
					setTimeout(function() {
						$('#side-menu').fadeIn(400);
					}, 200);

				} else if ($('body').hasClass('fixed-sidebar')) {
					$('#side-menu').hide();
					setTimeout(function() {
						$('#side-menu').fadeIn(400);
					}, 100);
				} else {
					// Remove all inline style from jquery fadeIn function to
					// reset menu state
					$('#side-menu').removeAttr('style');
				}
			}
		}
	};
};

function closeOffCanvas() {
	return {
		restrict : 'A',
		template : '<a class="close-canvas-menu" ng-click="closeOffCanvas()"><i class="fa fa-times"></i></a>',
		controller : function($scope, $element) {
			$scope.closeOffCanvas = function() {
				$("body").toggleClass("mini-navbar");
			}
		}
	};
}

/**
 * vectorMap - Directive for Vector map plugin
 */
function vectorMap() {
	return {
		restrict : 'A',
		scope : {
			myMapData : '=',
		},
		link : function(scope, element, attrs) {
			var map = element.vectorMap({
				map : 'world_mill_en',
				backgroundColor : "transparent",
				regionStyle : {
					initial : {
						fill : '#e4e4e4',
						"fill-opacity" : 0.9,
						stroke : 'none',
						"stroke-width" : 0,
						"stroke-opacity" : 0
					}
				},
				series : {
					regions : [ {
						values : scope.myMapData,
						scale : [ "#1ab394", "#22d6b1" ],
						normalizeFunction : 'polynomial'
					} ]
				},
			});
			var destroyMap = function() {
				element.remove();
			};
			scope.$on('$destroy', function() {
				destroyMap();
			});
		}
	}
}

/**
 * sparkline - Directive for Sparkline chart
 */
function sparkline() {
	return {
		restrict : 'A',
		scope : {
			sparkData : '=',
			sparkOptions : '=',
		},
		link : function(scope, element, attrs) {
			scope.$watch(scope.sparkData, function() {
				render();
			});
			scope.$watch(scope.sparkOptions, function() {
				render();
			});
			var render = function() {
				$(element).sparkline(scope.sparkData, scope.sparkOptions);
			};
		}
	}
};

/**
 * icheck - Directive for custom checkbox icheck
 */
// .directive('onFinishRender',['$timeout', '$parse', function ($timeout,
// $parse) {
function onFinishRender($timeout, $parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attr) {
			if (scope.$last === true) {
				$timeout(function() {
					scope.$emit('ngRepeatFinished'); // 事件通知
					var fun = $scope.$eval(attrs.onFinishRender);
					if (fun && typeof (fun) == 'function') {
						fun(); // 回调函数
					}
				});
			}
		}
	}
};

function icheck($timeout) {
	return {
		restrict : 'A',
		require : 'ngModel',
		link : function($scope, element, $attrs, ngModel) {
			return $timeout(function() {
				var value;
				value = $attrs['value'];

				$scope.$watch($attrs['ngModel'], function(newValue) {
					$(element).iCheck('update');
				})

				return $(element)
						.iCheck({
							checkboxClass : 'icheckbox_square-green',
							radioClass : 'iradio_square-green'

						})
						.on(
								'ifChanged',
								function(event) {
									if ($(element).attr('type') === 'checkbox'
											&& $attrs['ngModel']) {
										$scope
												.$apply(function() {
													return ngModel
															.$setViewValue(event.target.checked);
												});
									}
									if ($(element).attr('type') === 'radio'
											&& $attrs['ngModel']) {
										return $scope
												.$apply(function() {
													return ngModel
															.$setViewValue(value);
												});
									}
								});
			});
		}
	};
}

/**
 * ionRangeSlider - Directive for Ion Range Slider
 */
function ionRangeSlider() {
	return {
		restrict : 'A',
		scope : {
			rangeOptions : '='
		},
		link : function(scope, elem, attrs) {
			elem.ionRangeSlider(scope.rangeOptions);
		}
	}
}

/**
 * dropZone - Directive for Drag and drop zone file upload plugin
 */
function dropZone() {
	return {
		restrict : 'AE',
		scope : {
			dzconfig : '=',
			dzeventhandlers : '=',
			alias : '@'
		},
		link : function(scope, element, attrs) {

			// //创建对象
			// if (scope.dzconfig.maxFiles == null) {
			// scope.dzconfig.maxFiles = 1;
			// }
			// drop files here to uploads
			console.log(scope.dzconfig);
			if (typeof (scope.dzconfig.dictDefaultMessage) == "undefined") {
				scope.dzconfig.dictDefaultMessage = "点击上传文件";
			}

			scope.dzconfig.dictFallbackMessage = "Your browser does not support drag'n'drop file uploads."
			scope.dzconfig.dictFallbackText = "Please use the fallback form below to upload your files like in the olden days."
			scope.dzconfig.dictFileTooBig = "File is too big ({{filesize}}MiB). Max filesize: {{maxFilesize}}MiB."
			scope.dzconfig.dictInvalidFileType = "不能上传此类文件"
			scope.dzconfig.dictResponseError = "服务端错误代码:{{statusCode}}"
			scope.dzconfig.dictCancelUpload = "取消上传"
			scope.dzconfig.dictCancelUploadConfirmation = "Are you sure you want to cancel this upload?"
			scope.dzconfig.dictRemoveFile = "删除"
			scope.dzconfig.dictRemoveFileConfirmation = null
			scope.dzconfig.dictMaxFilesExceeded = "超过最大文件限制"

			var dzeventhandlers = {
				'addedfile' : function(file) {
					scope.file = file;
					if (this.files[1] != null) {
						this.removeFile(this.files[0]);
					}
					scope.$apply(function() {
						scope.fileAdded = true;
					});
				},

				'success' : function(file, response) {
				},
				'maxfilesexceeded' : function(file) {
					if (scope.dropzone.options.maxFiles == 1) {
						scope.dropzone.removeAllFiles()
						scope.dropzone.addFile(file);
					} else {
						scope.dropzone.removeFile(file);
					}
					message_error("已超过文件最大限制数目,"
							+ scope.dropzone.options.maxFiles);
				}

			};

			dropzone = new Dropzone(element[0], scope.dzconfig);

			// angular.forEach(dzeventhandlers, function(handler, event) {
			//				
			// dropzone.on(event, handler);
			// });
			// 派发前前端配置监听事件
			if (scope.dzeventhandlers) {
				Object.keys(scope.dzeventhandlers).forEach(function(eventName) {
					dropzone.on(eventName, scope.dzeventhandlers[eventName]);
				});
			}

			scope.processDropzone = function() {
				dropzone.processQueue();
			};

			scope.resetDropzone = function() {
				dropzone.removeAllFiles();
			}
		}
	}
}

/**
 * chatSlimScroll - Directive for slim scroll for small chat
 */
function chatSlimScroll($timeout) {
	return {
		restrict : 'A',
		link : function(scope, element) {
			$timeout(function() {
				element.slimscroll({
					height : '234px',
					railOpacity : 0.4
				});

			});
		}
	};
}

/**
 * customValid - Directive for custom validation example
 */
function customValid() {
	return {
		require : 'ngModel',
		link : function(scope, ele, attrs, c) {
			scope.$watch(attrs.ngModel, function() {

				// You can call a $http method here
				// Or create custom validation

				var validText = "Inspinia";

				if (scope.extras == validText) {
					c.$setValidity('cvalid', true);
				} else {
					c.$setValidity('cvalid', false);
				}

			});
		}
	}
}

/**
 * fullScroll - Directive for slimScroll with 100%
 */
function fullScroll($timeout) {
	return {
		restrict : 'A',
		link : function(scope, element) {
			$timeout(function() {
				element.slimscroll({
					height : '100%',
					railOpacity : 0.9
				});

			});
		}
	};
}

/**
 * slimScroll - Directive for slimScroll with custom height
 */
function slimScroll($timeout) {
	return {
		restrict : 'A',
		scope : {
			boxHeight : '@'
		},
		link : function(scope, element) {
			$timeout(function() {
				element.slimscroll({
					height : scope.boxHeight,
					railOpacity : 0.9
				});

			});
		}
	};
}

/**
 * clockPicker - Directive for clock picker plugin
 */
function clockPicker() {
	return {
		restrict : 'A',
		link : function(scope, element) {
			element.clockpicker();
		}
	};
};

/**
 * landingScrollspy - Directive for scrollspy in landing page
 */
function landingScrollspy() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			element.scrollspy({
				target : '.navbar-fixed-top',
				offset : 80
			});
		}
	}
}

/**
 * fitHeight - Directive for set height fit to window height
 */
function fitHeight() {
	return {
		restrict : 'A',
		link : function(scope, element) {
			element.css("height", $(window).height() + "px");
			element.css("min-height", $(window).height() + "px");
		}
	};
}

/**
 * truncate - Directive for truncate string
 */
function truncate($timeout) {
	return {
		restrict : 'A',
		scope : {
			truncateOptions : '='
		},
		link : function(scope, element) {
			$timeout(function() {
				element.dotdotdot(scope.truncateOptions);

			});
		}
	};
}

/**
 * touchSpin - Directive for Bootstrap TouchSpin
 */
function touchSpin() {
	return {
		restrict : 'A',
		scope : {
			spinOptions : '='
		},
		link : function(scope, element, attrs) {
			scope.$watch(scope.spinOptions, function() {
				render();
			});
			var render = function() {
				$(element).TouchSpin(scope.spinOptions);
			};
		}
	}
};

/**
 * markdownEditor - Directive for Bootstrap Markdown
 */
function markdownEditor() {
	return {
		restrict : "A",
		require : 'ngModel',
		link : function(scope, element, attrs, ngModel) {
			$(element).markdown({
				savable : false,
				onChange : function(e) {
					ngModel.$setViewValue(e.getContent());
				}
			});
		}
	}
};

/**
 * passwordMeter - Directive for jQuery Password Strength Meter
 */
function passwordMeter() {
	return {
		restrict : 'A',
		scope : {
			pwOptions : '='
		},
		link : function(scope, element, attrs) {
			scope.$watch(scope.pwOptions, function() {
				render();
			});
			var render = function() {
				$(element).pwstrength(scope.pwOptions);
			};
		}
	}
};

/**
 * 
 * Pass all functions into module
 */
angular.module('inspinia').directive('pageTitle', pageTitle).directive(
		'sideNavigation', sideNavigation).directive('iboxTools', iboxTools)
		.directive('minimalizaSidebar', minimalizaSidebar).directive(
				'vectorMap', vectorMap).directive('sparkline', sparkline)
		.directive('icheck', icheck)
		.directive('ionRangeSlider', ionRangeSlider).directive('dropZone',
				dropZone).directive('responsiveVideo', responsiveVideo)
		.directive('chatSlimScroll', chatSlimScroll).directive('customValid',
				customValid).directive('fullScroll', fullScroll).directive(
				'closeOffCanvas', closeOffCanvas).directive('clockPicker',
				clockPicker).directive('landingScrollspy', landingScrollspy)
		.directive('fitHeight', fitHeight).directive('iboxToolsFullScreen',
				iboxToolsFullScreen).directive('slimScroll', slimScroll)
		.directive('truncate', truncate).directive('touchSpin', touchSpin)
		.directive('markdownEditor', markdownEditor).directive('passwordMeter',
				passwordMeter).directive('onFinishRender', onFinishRender);
