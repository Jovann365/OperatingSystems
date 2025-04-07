#!/bin/bash

if [ $# -lt 2 ]
then
        echo "Enter a folder!"
        exit 1
fi

if [ ! -d $1 ]
then
        echo "Not a folder!"
        exit 1
fi

fileNames=`ls -Rl $1 | grep '^-' | awk '{print $9;}'`
for file in $fileNames
do
        location=`ls $1 | find -name "$file"`
        echo $location
        newName=`echo $location | sed 's/\//_/g' | sed 's/^\.//'`
        echo $newName
        mv "$location" "$2/$newName"
done