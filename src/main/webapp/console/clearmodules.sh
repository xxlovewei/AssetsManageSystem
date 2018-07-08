
basepath=$(cd `dirname $0`; pwd)
echo $basepath
mv node_modules /tmp/



exit 0
#########clear
echo "start clear"
basepath=$(cd `dirname $0`; pwd)
echo $basepath
cd $basepath/node_modules
rm -rf datatables.net
rm -rf datatables.net-dt
find ./ -type d -name "node_modules" -exec rm -rf {} \;
find ./ -type d -name "bower_components" -exec rm -rf {} \;
find ./ -type d -name "coverage" -exec rm -rf {} \;
find ./ -type d -name "demo" -exec rm -rf {} \;
find ./ -type d -name "src" -exec rm -rf {} \;
find ./ -type d -name "test" -exec rm -rf {} \;
find ./ -type d -name "less" -exec rm -rf {} \;
find ./ -type d -name "docs" -exec rm -rf {} \;
find ./ -type d -name "swf" -exec rm -rf {} \;
find ./ -type d -name "example" -exec rm -rf {} \;
find ./  -name "*.html" -exec rm -rf {} \;
find ./  -name "*.md" -exec rm -rf {} \;
find ./  -name "yarn.lock" -exec rm -rf {} \;
find ./  -name "*.sh" -exec rm -rf {} \;
find ./  -name "*.txt" -exec rm -rf {} \;
find ./  -name "package.json" -exec rm -rf {} \;
find ./  -name "*LICENSE*" -exec rm -rf {} \;
find ./  -name "*runtfile.js" -exec rm -rf {} \;
find ./  -name "gulpfile.js" -exec rm -rf {} \;
find ./  -name "bower.json" -exec rm -rf {} \;
sleep 5