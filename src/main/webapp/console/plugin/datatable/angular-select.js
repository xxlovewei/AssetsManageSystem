/*!
 * angular-datatables - v0.6.2
 * https://github.com/l-lin/angular-datatables
 * License: MIT
 */
"undefined"!=typeof module&&"undefined"!=typeof exports&&module.exports===exports&&(module.exports="datatables.select"),function(a,b,c,d){"use strict";function e(a){function b(a){function b(a,b){function c(a){if(d.isUndefined(a))throw new Error("You must define the options for the select extension. See https://datatables.net/reference/option/#select");return e.select=a,e}var e=a(b);return e.withSelect=c,e}var c=a.newOptions,e=a.fromSource,f=a.fromFnPromise;return a.newOptions=function(){return b(c)},a.fromSource=function(a){return b(e,a)},a.fromFnPromise=function(a){return b(f,a)},a}a.decorator("DTOptionsBuilder",b),b.$inject=["$delegate"]}d.module("datatables.select",["datatables"]).config(e),e.$inject=["$provide"]}(window,document,jQuery,angular);