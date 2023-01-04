@ECHO OFF
cd classesPackage
javac -d .. *.java
cd ..
javac -cp ".;./opencsv-5.7.1.jar" Main.java
java Main.java 