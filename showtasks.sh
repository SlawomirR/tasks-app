#!/bin/bash
if ./runcrud.sh; then
#    firefox -new-window http://localhost:8081/crud/v1/task/getTasks
    lynx -dump http://localhost:8081/crud/v1/task/getTasks
else
    echo "There were some errors."
fi
