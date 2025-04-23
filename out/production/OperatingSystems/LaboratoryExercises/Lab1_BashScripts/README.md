# Задача 1 

1. Креирајте директориуми со име directory1 и directory2 и во првиот директориум додади датотека со име file123. / Create directories with names directory1 and directory2 and in the first directory add a file named file123.
2. Напишете команда со која ќе ја прочитате помошната документација за командата ls. / Write a command to read the manual for the command ls.
3. Променете ги пермисиите на фајлот file123 така што истиот може да го читаат и извршуваат само корисникот и неговата група. / Change the file permissions for the file named file123 so that it can be read and executed by the user and the group.
4. Направете го претходното барање на друг начин. / Do the previous request in another way.
5. Напишете команда со која ќе добиете информација кој сé е тековно најавен на системот./ Write a command to get the info about the users which are currently logged in on the system.
6. Премести ја креираната датотека од directory1 во directory2 при што ќе го смените името на датотеката во file234 / Move the created file from directory1 to directory2 and change the name of the file to file234
7. Избришете го директориумот directory1 кој го креиравте во рамки на оваа вежба заедно со целосната негова содржина. / Delete directory1 created in this exercise together with its content.

# Задача 2

1. Креирајте директориум со име results и во него csv датотеки со име OS1.txt и OS2.txt / Create a folder with the name results and in it csv files named OS1.txt and OS2.txt.
2. Пополнете ги креираните датотеки со податоци за студентите кои го полагале предметот во формат: индекс датум статус поени. (преземете ја содржината од линковите) / Fill in the files you created with data for the students that took the exam for the course using the format: index date status points. (get the content of the files from the links below)
3. Прикажете ја на екран содржината на креираните датотеки одделно, но и заедно. / Show the content for these files on screen, separately and together.
4. Креирајте датотека со име total.txt во која ќе ги ископирате податоците за сите студенти од сите предмети. / Create a file named total.txt that will have the data for all students from all courses.
5. Преместете ја датотеката total.txt во директориумот кој е дете на results, а се вика results_2023. / Move the file total.txt in a folder that is a child of results and is named results_2023.
6. Избројте колку редови, зборови и знаци има во датотеката results.txt. / Count the number of rows, words and signs in the file results.txt.
7. Прикажете ги на екран податоците само за студентите кои го започнале студирањето во 2022та година. / Show on the screen the data for the students that have enrolled the Faculty in 2022.
8. Прикажете ги на екран само индекс, датум, поени на студентите кои го започнале студирањето во 2023та година и имаат над 50 поени на полагањето. / Show on the screen the names of the students, date and points that have enrolled the Faculty in 2023 and have more than 50 points.
9. Пребројте колку студенти го немаат завршено квизот од 14.03.2024 (статус in_progress). / Count how many students have not finished the quiz on 14.03.2024 with status in_progress

# Задача 3

Напишете командна процедура која ќе прима произволен број на влезни аргументи кои означуваат времиња на извршување на програма изразени во минути.

пример - **bash script.sh 5 7 15 8 22 6**

Потребно е процедурата да пресмета и испечати просечно времетраење на програмата според првите три извршувања (5, 7, 15 во примерот) претставено во секунди, како и бројот на извршувања на програмата (број на направени/внесени мерења).

Доколку бројот на влезни аргументи е поголем или еднаков на 5, потребно е да се испечати 'The testing is done', додека доколку е помал од 5 - 'More testing is needed'.

Излезот на примерот треба да биде следниот:<br>
**Average execution time: 540<br>
Count of executions: 6<br>
The testing is done**
