#!/bin/bash

allFiles=`ls`

select file in $allFiles "Exit Program"
do
        if [ -z "$file" ]
        then
                echo "Empty"
                continue
        fi
        if [ "$file" = "Exit Program" ]
        then
                break
        fi
        if [ ! -f "$file" ]
        then
                echo "Error"
                continue
        else
                cat $file
        fi
done