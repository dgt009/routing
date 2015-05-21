java -cp target/routing-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.mds.routing.execution.mainExecution -i 7394137 -dbh localhost -dbu postgres -dbp hhh -tname vessels -cname imo
if [ $? -eq 0 ]; then
    echo "OK"
fi
if [ $? -eq 1 ]; then
    echo "Not FOUND"
fi

if [ $? -eq 2 ]; then
    echo "Found but invalid"
fi

