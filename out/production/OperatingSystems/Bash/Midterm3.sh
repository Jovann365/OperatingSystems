#!/bin/bash

if [ $# -ne 1 ]
then
        echo "Enter an arg"
        exit 1
fi

if [ ! -f $1 ]
then
        echo "Not a file"
        exit 1
fi

newFile="passed_$1"
touch $newFile
echo "Surname,\"First name\",Index,Points" | cat > $newFile
awk -F, 'BEGIN{count=0; sum=0;} {if($4>=5){count+=1;sum+=$4;print;}} END{print "Passed students average points:",(sum/count)}' $1 | cat >> $newFile