#!/bin/bash

names=$1
rename=$2

shift
shift

for file in $*
do
        if [ -f "$file" ]
        then
                newname=`echo "$file" | sed "s/${names}/${rename}/g"`
                if [ -f "$newname" ]
                then
                        echo "Error"
                else
                        mv $file $newname
                fi
        fi
done