@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
REM Updates: Points to memz\Memz.java. Java will find the dependencies (Ui, Tasks) automatically.
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\memz\Memz.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM Updates: Runs the class "memz.Memz" (Package.ClassName)
java -classpath ..\bin memz.Memz < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT