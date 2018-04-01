#!/bin/bash
export CATALINA_HOME="/usr/share/tomcat8"

CATALINA_LIB="/var/lib/tomcat8"
SOURCE_PATH="build/libs"
CRUD_WAR=$SOURCE_PATH/"crud.war"
TASKS_WAR=$SOURCE_PATH/"tasks-0.0.1-SNAPSHOT.war"

stop_tomcat() {
    sudo $CATALINA_HOME/bin/catalina.sh stop
}

start_tomcat() {
    sudo $CATALINA_HOME/bin/catalina.sh start
    end
}

rename() {
    rm -v $CRUD_WAR
    if mv -v $TASKS_WAR $CRUD_WAR; then
        echo "Successfully renamed file"
    else
        "Cannot rename file"
        fail
    fi
}

copy_file() {
    if sudo cp -v $CRUD_WAR $CATALINA_LIB/webapps/; then
        #stop_tomcat && start_tomcat
        systemctl restart tomcat8.service
        sudo -k
    else
        fail
    fi
}

fail() {
    echo "There were errors"
    return 1
}

end() {
    echo "Work is finished"
    return 0
}

if ./gradlew build; then
    rename
    copy_file
else
    stop_tomcat
    fail
fi
