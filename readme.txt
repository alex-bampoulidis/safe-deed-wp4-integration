- Start the Spring Boot web server. By default it starts at port 9090.
- Command "java -jar safedeedriskanalysis-1.0-SNAPSHOT"

--------------------------------------------------------------------------------------

Data

In the folder "sampledata" you will find the dataset "adult" which is the standard one used in such tasks

-------------------------------------------------------------------------------------------

Risk Analysis API

POST /analysetabular?input=&separator=&qis=
input is the full path to the input file
separator is the character that separates the attributes
qis is the list of QIs written as a string separated by the specified separator

returns a JSON response (example response is /sampledata/test-response.txt)

example:
http://localhost:9090/analysetabular?input=C:/Users/alexb/Desktop/work/safe-deed/wp4-integration/sampledata/adult.csv&separator=;&qis=sex;salary-class;race;workclass;marital-status;occupation;education;native-country;age

----------------------------------------------------------------------------------------------

Visualisation

http://localhost:9090/viz.html

This is a simple html/javascript page that visualises the output of the Risk Analysis API (takes the example of /sampledata/test-response.txt)

----------------------------------------------------------------------------------------------

K-Anonymisation API

POST /anonymise?input=&output=&separator=&qis=&k=
input is the full path to the input file
output is the full path to the output file
separator is the character that separates the attributes
qis is the list of QIs written as a string separated by the specified separator
k is the desire k-anonymity value

returns a "K-Anonymisation Completed" message after completion

example:
http://localhost:9090/anonymise?input=C:/Users/alexb/Desktop/work/safe-deed/wp4-integration/sampledata/adult.csv&output=C:/Users/alexb/Desktop/work/safe-deed/wp4-integration/sampledata/anon.csv&separator=;&qis=sex;salary-class;race;workclass;marital-status;occupation;education;native-country;age&k=2