echo "start clear"
basepath=$(cd `dirname $0`; pwd)
echo $basepath
cd $basepath/node_modules
rm -rf datatables.net
rm -rf datatables.net-dt
rm -rf "@uirouter"
find ./ -type d -name "node_modules" -exec rm -rf {} \;
find ./ -type d -name "demo" -exec rm -rf {} \;
find ./ -type d -name "src" -exec rm -rf {} \;
find ./ -type d -name "test" -exec rm -rf {} \;



sleep 5