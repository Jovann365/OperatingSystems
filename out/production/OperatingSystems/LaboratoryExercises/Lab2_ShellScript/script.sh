#!/bin/bash

if [ $# == 0 ]
then
        echo "You have not entered any arguments!"
        exit 1
fi

if [ ! -f "$1" ]
then
        echo "Please anter a valid file!"
        exit 1
fi

fileContent=`cat $1`
if [ -z "$fileContent" ]
then
        echo "You have entered an empty file!"
        exit 1
fi

newFile="temp.csv"
`awk -F, '{ if($2 != "Name") print;}' $1 > $newFile`

total=`wc -l $newFile | awk '{print $1;}'`

mathTotal=`awk -F, 'BEGIN{count = 0;} {count += $3} END{print count;}' $newFile`
scienceTotal=`awk -F, 'BEGIN{count =0;} {count += $4} END{print count;}' $newFile`
englishTotal=`awk -F, 'BEGIN{count =0;} {count += $5} END{print count;}' $newFile`
historyTotal=`awk -F, 'BEGIN{count =0;} {count += $6} END{print count;}' $newFile`

mathAverage=`echo "scale=2; $mathTotal / $total" | bc`
scienceAverage=`echo "scale=2; $scienceTotal / $total" | bc`
englishAverage=`echo "scale=2; $englishTotal / $total" | bc`
historyAverage=`echo "scale=2; $historyTotal / $total" | bc`

mathBest=`awk -F, 'BEGIN{name=""; score=0;}
        {if($3 > score) {score=$3; name=$2;}}
        END{print name,"(Score:",score ")";}
' $newFile`

scienceBest=`awk -F, 'BEGIN{name=""; score=0;}
        {if($4 > score) {score=$4; name=$2;}}
        END{print name,"(Score:",score ")";}
' $newFile`

englishBest=`awk -F, 'BEGIN{name=""; score=0;}
        {if($5 > score) {score=$5; name=$2;}}
        END{print name,"(Score:",score ")";}
' $newFile`

historyBest=`awk -F, 'BEGIN{name=""; score=0;}
        {if($6 > score) {score=$6; name=$2;}}
        END{print name,"(Score:",score ")";}
' $newFile`

mathWorst=`awk -F, 'BEGIN{name=""; score=100;}
        {if($3 < score) {score=$3; name=$2;}}
        END{print name,"(Score:",score ")";}
' $newFile`

scienceWorst=`awk -F, 'BEGIN{name=""; score=100;}
        {if($4 < score) {score=$4; name=$2;}}
        END{print name,"(Score:",score ")";}
' $newFile`

englishWorst=`awk -F, 'BEGIN{name=""; score=100;}
        {if($5 < score) {score=$5; name=$2;}}
        END{print name,"(Score:",score ")";}
' $newFile`

historyWorst=`awk -F, 'BEGIN{name=""; score=100;}
        {if($6 < score) {score=$6; name=$2;}}
        END{print name,"(Score:",score ")";}
' $newFile`

echo "Exam Scores Analysis"
echo "-------------------"
echo "Total Number of Students: $total"
echo
echo "Subject Averages:"
echo " Math:     $mathAverage"
echo " Science:  $scienceAverage"
echo " English:  $englishAverage"
echo " History:  $historyAverage"
echo
echo "Subject Extreme Performers:"
echo " Math - Highest: $mathBest, Lowest: $mathWorst"
echo " Science - Highest: $scienceBest, Lowest: $scienceWorst"
echo " English - Highest: $englishBest, Lowest: $englishWorst"
echo " Histoy - Highest: $historyBest, Lowest: $historyWorst"

rm $newFile
