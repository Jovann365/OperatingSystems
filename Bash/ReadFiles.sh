#!/bin/bash

files=$*

for file in $files
do
        if [ -r $file ]
        then
                cat $file
        fi
done