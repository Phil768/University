@ECHO OFF
cd classesPackage
javac -d .. *.java
cd ..
javac Main.java
java Main.java