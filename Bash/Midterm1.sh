  GNU nano 7.2                                           results.sh                                                     #!/bin/bash

if [ $# -lt 1 ]
then
        echo "Enter a folder!"
        exit 1
fi

if [ ! -d $1 ]
then
        echo "Not a folder!"
        exit 1
fi

temp="temp.rez"

fileNames=`ls -R "$1" | grep '\.rez$'`


for file in $fileNames
do
        location=`ls $1 | find -name "$file" | awk '{print;}'`
        cat $location >> $temp
done

awk -F, '$1 ~ /w/ || $1 ~ /d/ {print $2;}' $temp | sort | uniq -c

rm $temp