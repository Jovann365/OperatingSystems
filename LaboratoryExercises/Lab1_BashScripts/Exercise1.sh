#!/usr/bin/env bash

mkdir directory1
mkdir directory2
cd directory1 || exit
tocuh file123
cd ../
man ls
chmod ug=rx,o= file123
chmod 550 file123
who
mv file123 ../directory2/file234
rm -r directory1