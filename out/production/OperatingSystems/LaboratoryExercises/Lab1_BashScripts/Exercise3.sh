#!/bin/bash

n=$(((${1}+${2}+${3})/3*60))

echo "Average execution time: ${n}"
echo "Count of executions: $#"

if [ $# -gt 5 ]
then
        echo "The testing is done"
else
        echo "More testing is needed"
fi