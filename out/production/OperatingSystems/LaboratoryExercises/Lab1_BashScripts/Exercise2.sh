#!/usr/bin/env bash

mkdir results
cd results || exit
touch OS1.txt
touch OS2.txt
nano OS1.txt
nano OS2.txt
cat OS1.txt
cat OS2.txt
cat OS1.txt OS2.txt
cat OS1.txt OS2.txt > total.txt
mkdir results_2023
mv total.txt ./results_2023
wc total.txt
awk '$1~/^22/ {print;}' total.txt
awk '$1~/^23/ && $4>50 {print $1,$2,$4;}' total.txt
awk '$3~/in_progress/ {print;}' total.txt | wc -l
