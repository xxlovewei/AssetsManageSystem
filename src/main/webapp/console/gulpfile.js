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

var lib_dir = "./node_modules/";
gulp.task('appjs', function() {
	gulp.src(
			[ './js/config_system.js', './js/config_wx.js',
					'./js/config_wx.js', './js/config_mshop.js',
					'./js/config_shop.js', './js/config_om.js',
					'./js/app_main.js', './js/service_user.js',
					'./js/translations.js', './js/directives.js',
					'./js/controllers.js' ]) // 数组顺序表示合并的顺序
	.pipe(concat('app.js')) // 先合并成新文件
	.pipe(gulp.dest('./js')) // 合并后存放路径
	.pipe(uglify()) // 压缩-未加密
	.pipe(rename('app.min.js')) // 压缩文件重命名
	.pipe(gulp.dest('./js')); // 存放路径
});

gulp.task('appcss', function() {
	gulp.src(
			[ './css/beforeload.css',
					'./node_modules/bootstrap/dist/css/bootstrap.min.css',
					'./css/animate.css',
					'./css/style.css' ]) // 数组顺序表示合并的顺序
	.pipe(concat('app.css')) // 先合并成新文件
	.pipe(gulp.dest('./css')) // 合并后存放路径
	.pipe(cleanCSS()) // 压缩-未加密
	.pipe(rename('app.min.css')) // 压缩文件重命名
	.pipe(gulp.dest('./css')); // 存放路径
});

gulp.task('copy:vendor',
		function() {
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

			gulp.src([ lib_dir + 'angular-ui-bootstrap/dist/**/*' ]).pipe(
					gulp.dest("./vendor/angular-ui-bootstrap/dist"));

			gulp.src([ lib_dir + 'angular-swx-local-storage/release/*' ]).pipe(
					gulp.dest("./vendor/angular-swx-local-storage/release"));

			gulp.src([ lib_dir + 'angular-chosen/dist/**/*' ]).pipe(
					gulp.dest("./vendor/angular-chosen/dist"));

			gulp.src([ lib_dir + 'angular-translate/dist/angular-translate.min.js' ]).pipe(
					gulp.dest("./vendor/angular-translate/dist"));

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

			gulp.src([ lib_dir + 'pace/**/**/*' ]).pipe(
					gulp.dest("./vendor/pace"));

			return "";
		});

gulp.task('test', function() {
	var jsFilter = filter('**/*.js', {
		restore : true
	});
	var cssFilter = filter('**/*.css', {
		restore : true
	});
	var indexHtmlFilter = filter([ '**/*', '!**/index.html' ], {
		restore : true
	});
	return gulp.src('js/*.js')/* 需要处理的文件 */
	.pipe(useref())/* 处理注释压缩 */
	.pipe(jsFilter)/* 筛选js文件 */
	.pipe(uglify())/* 压缩js文件 */
	.pipe(jsFilter.restore)/* 放回流里 */
	.pipe(cssFilter)/* 筛选css文件 */
	.pipe(csso())/* 压缩css文件 */
	.pipe(cssFilter.restore)/* 放回流里 */
	.pipe(indexHtmlFilter)/* 筛选html文件 */
	.pipe(rev())/* 生成哈希版本号 */
	.pipe(indexHtmlFilter.restore)/* 放回流里 */
	.pipe(revReplace())/* 更新index引用 */
	.pipe(gulp.dest('dist'));/* 文件流放到dist目录下 */

});

gulp.task('clean', function() {
	console.log('清空 dist 目录下的资源')
	return gulp.src(
			[ './vendor/', './css/app.min.css', './css/app.css', './js/app.js',
					'./js/app.min.js' ], {
				read : false
			}).pipe(clean({
		force : true
	}));
});

gulp.task('default', function() {
	gulp.src('js/*.js') // 路径问题：gulpfile.js为路径的起点。此路径表示js文件下的所有js文件。
	.pipe(concat('all.js')) // 合并成的js文件名称
	.pipe(uglify()) //压缩
	.pipe(gulp.dest('build')); //打包压缩在build目录下。
});

gulp.task('install', sequence('clean', 'appjs', 'appcss','copy:vendor'));
