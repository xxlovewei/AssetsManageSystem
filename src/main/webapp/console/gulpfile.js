var gulp = require('gulp');
var rev = require('gulp-rev');/* 给文件用哈希码添加版本号 */
var revReplace = require('gulp-rev-replace');/* 更新引用 */
var useref = require('gulp-useref');/* 合并文件 */
var filter = require('gulp-filter');/* 过滤器：筛选，恢复 */
var uglify = require('gulp-uglify');/* 压缩js */
var csso = require('gulp-csso');/* 压缩css */
var concat = require('gulp-concat');
var rename = require('gulp-rename');
var sequence = require('gulp-sequence');
var clean = require('gulp-clean');
var cleanCSS = require('gulp-clean-css');// 压缩css
var assetRev = require('gulp-asset-rev');
cssver = require('gulp-make-css-url-version');
var revCollector = require('gulp-rev-collector');

var lib_dir = "./node_modules/";


/*=====================压缩app js文件==========================*/
gulp.task('app_js',
		function() {
			var js_list = [ './js/config_system.js', './js/config_wx.js',
					'./js/config_wx.js', './js/config_mshop.js',
					'./js/config_shop.js', './js/config_om.js',
					'./js/app_main.js', './js/service_user.js',
					'./js/translations.js', './js/directives.js',
					'./js/controllers.js' ];
			console.log("合并js文件");
			console.log(js_list);
			gulp.src(js_list) // 数组顺序表示合并的顺序
			.pipe(concat('app.js')) // 先合并成新文件
			.pipe(gulp.dest('./js')) // 存放路径
			.pipe(rev())
			.pipe(gulp.dest('./js'))// 合并后存放路径
			.pipe(rev.manifest())
			.pipe(gulp.dest('./rev/app_js/'));
		});


/*=====================压缩公共 js文件==========================*/
gulp
		.task(
				'app_libjs',
				function() {
					var js_list = [
							'vendor/jquery/dist/jquery.min.js',
							'vendor/bootstrap/dist/js/bootstrap.min.js',
							'vendor/metismenu/dist/metisMenu.min.js',
							'vendor/jquery-slimscroll/jquery.slimscroll.min.js',
							'js/base.js',
							'vendor/angular/angular.min.js',
							'vendor/angular-sanitize/angular-sanitize.min.js',
							'vendor/oclazyload/dist/ocLazyLoad.min.js',
							'vendor/angular-translate/dist/angular-translate.min.js',
							'vendor/angular-ui-route/angularjs/release/angular-ui-router.min.js',
							'vendor/ng-idle/angular-idle.js',
							'plugin/ui-bootstrap/ui-bootstrap-tpls-1.1.2.min.js',
							'plugin/angular-notify/angular-notify.min.js',
							'vendor/angular-loading-bar/build/loading-bar.min.js',
							'vendor/dropzone/dist/min/dropzone.min.js',
							'vendor/chosen-js/chosen.jquery.min.js',
							'vendor/angular-chosen-localytics/dist/angular-chosen.min.js',
							'vendor/jstree/dist/jstree.min.js',
							'vendor/ng-js-tree/dist/ngJsTree.min.js',
							'vendor/datatables/media/js/jquery.dataTables.min.js',
							'plugin/datatable/dataTables.bootstrap.min.1.10.16.js',
							'vendor/angular-datatables/dist/angular-datatables.min.js',
							'plugin/datatable/angular-datatables.buttons0.6.2.min.js',
							'plugin/datatable/dataTables.buttons1.22.min.js',
							'vendor/datatables-responsive/js/dataTables.responsive.js',
							'vendor/datatables-select/dist/js/dataTables.select.min.js',
							'vendor/angular-datatables/dist/plugins/select/angular-datatables.select.min.js',
							'vendor/datatables.net-fixedcolumns/js/dataTables.fixedColumns.min.js',
							'vendor/angular-datatables/dist/plugins/fixedcolumns/angular-datatables.fixedcolumns.min.js',
							'vendor/angular-ueditor/dist/angular-ueditor.js',
							'plugin/moment/moment.min.js',
							'plugin/datapicker/angular-datepicker.js',
							'vendor/angular-confirm/angular-confirm.min.js',
							'vendor/angular-swx-local-storage/release/swx-local-storage.min.js' ];
					console.log("合并js文件");
					console.log(js_list);
					gulp.src(js_list) // 数组顺序表示合并的顺序
					.pipe(concat('applib.min.js')) // 先合并成新文件
					.pipe(gulp.dest('./js')) // 存放路径
					.pipe(rev())
					.pipe(gulp.dest('./js'))// 合并后存放路径
					.pipe(rev.manifest())
					.pipe(gulp.dest('./rev/app_libjs/'));
				});



/*=====================压缩公共 css文件==========================*/
gulp.task('app_libcss', function() {
	var css_list = [ 'plugin/angular-notify/angular-notify.min.css"',
			'plugin/angular-notify/angular-notify.min.css',
			'vendor/angular-loading-bar/build/loading-bar.min.css',
			'vendor/dropzone/dist/min/basic.min.css',
			'plugin/chosen/bootstrap-chosen.min.css',
			'vendor/jstree/dist/themes/default/style.min.css',
			'plugin/datatable/dataTables.bootstrap.1.10.16.css',
			'plugin/datatable/buttons.bootstrap.min.1.2.2.css',
			'plugin/datatable/responsive.dataTables.min.css',
			'plugin/datatable/fixedColumns.bootstrap3.2.6.css',
			'vendor/datatables-select/dist/css/select.dataTables.min.css',
			'plugin/datapicker/angular-datapicker.css' ];
	console.log("合并css文件");
	console.log(css_list);
	gulp.src(css_list) // 数组顺序表示合并的顺序
	.pipe(concat('applib.min.css')) // 先合并成新文件
	.pipe(cleanCSS()).
	pipe(gulp.dest('./css')). // 存放路径
	pipe(rev()).
	pipe(gulp.dest('./css')) // 存放路径
	.pipe(rev.manifest()).
	pipe(gulp.dest('./rev/app_libcss/'));

});


/*=====================压缩app css文件==========================*/
gulp.task('app_css', function() {
	var css_list = [ './css/beforeload.css',
			'./node_modules/bootstrap/dist/css/bootstrap.min.css',
			'./css/animate.css', './css/style.css' ];
	console.log("合并css文件");
	console.log(css_list);
	gulp.src(css_list) // 数组顺序表示合并的顺序
	.pipe(concat('app.min.css')) // 先合并成新文件
	.pipe(cleanCSS()).
	pipe(gulp.dest('./css')). // 存放路径
	pipe(rev()).
	pipe(gulp.dest('./css')) // 存放路径
	.pipe(rev.manifest()).
	pipe(gulp.dest('./rev/app_css/'));

});


gulp.task('revHtml', function () {
    return gulp.src(['rev/**/*.json', './tpl/min/index.html'])
        .pipe(revCollector())
        .pipe(gulp.dest('./'));
});


/*=====================copy库文件==========================*/
gulp.task('copy:vendor', function() {
	gulp.src([ lib_dir + 'angular-confirm/angular-confirm.min.js' ]).pipe(
			gulp.dest("./vendor/angular-confirm"));

	gulp.src([ lib_dir + 'angular-swx-local-storage/release/**/*' ]).pipe(
			gulp.dest("./vendor/angular-swx-local-storage/release"));

	gulp.src([ lib_dir + '@uirouter/angularjs/release/*' ]).pipe(
			gulp.dest("./vendor/angular-ui-route/angularjs/release"));

	gulp.src([ lib_dir + 'chosen-js/**/**/*' ]).pipe(
			gulp.dest("./vendor/chosen-js"));

	gulp.src([ lib_dir + 'jquery/dist/**/*' ]).pipe(
			gulp.dest("./vendor/jquery/dist"));

	gulp.src([ lib_dir + 'angular/**/**/*' ]).pipe(
			gulp.dest("./vendor/angular"));

	gulp.src([ lib_dir + 'datatables/**/**/*' ]).pipe(
			gulp.dest("./vendor/datatables"));

	gulp.src([ lib_dir + 'angular-chosen-localytics/dist/**/*' ]).pipe(
			gulp.dest("./vendor/angular-chosen-localytics/dist"));

	gulp.src([ lib_dir + 'angular-loading-bar/build/**/*' ]).pipe(
			gulp.dest("./vendor/angular-loading-bar/build"));

	gulp.src([ lib_dir + 'angular-datatables/dist/**/*' ]).pipe(
			gulp.dest("./vendor/angular-datatables/dist"));

	gulp.src([ lib_dir + 'datatables.net-fixedcolumns/**/*' ]).pipe(
			gulp.dest("./vendor/datatables.net-fixedcolumns"));
	
	
 
	gulp.src([ lib_dir + 'angular-ui-bootstrap/dist/**/*' ]).pipe(
			gulp.dest("./vendor/angular-ui-bootstrap/dist"));

	gulp.src([ lib_dir + 'angular-swx-local-storage/release/*' ]).pipe(
			gulp.dest("./vendor/angular-swx-local-storage/release"));

	gulp.src([ lib_dir + 'angular-chosen/dist/**/*' ]).pipe(
			gulp.dest("./vendor/angular-chosen/dist"));

	gulp.src([ lib_dir + 'angular-translate/dist/angular-translate.min.js' ])
			.pipe(gulp.dest("./vendor/angular-translate/dist"));

	gulp.src([ lib_dir + 'angular-ueditor/dist/**/*' ]).pipe(
			gulp.dest("./vendor/angular-ueditor/dist"));

	gulp.src([ lib_dir + 'bootstrap/dist/**/*' ]).pipe(
			gulp.dest("./vendor/bootstrap/dist"));

	gulp.src([ lib_dir + 'angular-sanitize/**/**/*' ]).pipe(
			gulp.dest("./vendor/angular-sanitize"));

	gulp.src([ lib_dir + 'dropzone/dist/**/*' ]).pipe(
			gulp.dest("./vendor/dropzone/dist"));

	gulp.src([ lib_dir + 'datatables-responsive/**/**/*' ]).pipe(
			gulp.dest("./vendor/datatables-responsive"));

	gulp.src([ lib_dir + 'datatables-buttons/**/**/*' ]).pipe(
			gulp.dest("./vendor/datatables-buttons"));

	gulp.src([ lib_dir + 'datatables-select/**/**/*' ]).pipe(
			gulp.dest("./vendor/datatables-select"));

	gulp.src([ lib_dir + 'bootstrap-duallistbox/dist/**/*' ]).pipe(
			gulp.dest("./vendor/bootstrap-duallistbox/dist"));

	gulp.src([ lib_dir + 'jquery-slimscroll/jquery.slimscroll.min.js' ]).pipe(
			gulp.dest("./vendor/jquery-slimscroll"));

	gulp.src([ lib_dir + 'metismenu/dist/**/*' ]).pipe(
			gulp.dest("./vendor/metismenu/dist"));

	gulp.src([ lib_dir + 'ng-js-tree/dist/**/*' ]).pipe(
			gulp.dest("./vendor/ng-js-tree/dist"));

	gulp.src([ lib_dir + 'ng-idle/**/**/*' ]).pipe(
			gulp.dest("./vendor/ng-idle"));

	gulp.src([ lib_dir + 'jstree/dist/**/*' ]).pipe(
			gulp.dest("./vendor/jstree/dist"));

	gulp.src([ lib_dir + 'oclazyload/dist/**/*' ]).pipe(
			gulp.dest("./vendor/oclazyload/dist"));

	gulp.src([ lib_dir + 'pace/**/**/*' ]).pipe(gulp.dest("./vendor/pace"));

	return "";
});


/*=====================清理构建目录==========================*/
gulp.task('clean', function() {
	console.log('清空资源');
	var c_ct = [ './css/app.min.css', './css/app.css', './js/app.js',
			'./js/app.min.js', './js/applib.min.js', './js/applib.min.css' ];
	//c_ct.push('./vendor/');
	console.log(c_ct);
	return gulp.src(c_ct, {
		read : false
	}).pipe(clean({
		force : true
	}));
});


 
gulp.task('deploy', sequence('clean', 'app_js', 'app_css'));

gulp.task('deploy:full', sequence('clean', 'app_js', 'app_css', 'app_libcss',
		'app_libjs', 'copy:vendor','revHtml'));
