/**
 * INSPINIA - Responsive Admin Theme
 * 2.7
 *
 * Custom scripts
 */

$(document).ready(function () {

    // Full height of sidebar
    function fix_height() {
 
        var heightWithoutNavbar = $("#wrapper").height() - 61;
        $(".sidebar-panel").css("min-height", heightWithoutNavbar + "px");

        var navbarHeight = $('nav.navbar-default').height();
 
        var wrapperHeigh = $('#page-wrapper').height();

        //$(".sidebar-panel").css("min-height", wrapperHeigh - 61 + "px");

        if(navbarHeight > wrapperHeigh){
            $('#page-wrapper').css("min-height", navbarHeight + "px");
        }

        if(navbarHeight < wrapperHeigh){
            $('#page-wrapper').css("min-height", $(window).height()  + "px");
        }

        if ($('body').hasClass('fixed-nav')) {
            if (navbarHeight > wrapperHeigh) {
                $('#page-wrapper').css("min-height", navbarHeight + "px");
            } else {
                $('#page-wrapper').css("min-height", $(window).height() - 60 + "px");
            }
        }

    }


    $(window).bind("load resize scroll", function() {
        if(!$("body").hasClass('body-small')) {
  
        	fix_height();
        }
    });

    // Move right sidebar top after scroll
    $(window).scroll(function(){
        if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav') ) {
            $('#right-sidebar').addClass('sidebar-top');
        } else {
            $('#right-sidebar').removeClass('sidebar-top');
        }
    });


    setTimeout(function(){
        fix_height();
    })
    
});

// Minimalize menu when screen is less than 768px
$(window).bind("load resize", function () {
    if ($(document).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }
});

/*********************常用函数*****************************/
//生产UUid
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

function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}


/*********************datatable*****************************/
function dt_renderUDAction(data, type, full) {
	var acthtml = " <div class=\"btn-group\"> ";
	acthtml = acthtml + " <button ng-click=\"update('" + full.id
			+ "')\" class=\"btn-white btn btn-xs\">更新</button>   ";
	acthtml = acthtml + " <button ng-click=\"del('" + full.id
	+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
	return acthtml;
}

function dt_renderMapSimple(data,type,full,map){
	if(typeof(map) == undefined){
		return "unknow";
	}
	for(var i=0;i<map.length;i++){
		if(map[i].id=data){
			return map[i].name;
		}
	}
	return "unknow"; 
}
/*********************datatable end*****************************/
