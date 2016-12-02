# JUnit Tests

The following tests are JUnit tests designed to test the methods of each entity in our system. All JUnit tests were made in Eclipse and can be run in Eclipse, similar to any other JUnit test files. This is the easiest way to run the test files, especially if you have Eclipse already installed. The test code can be built and compiled in the same way as the client. The tests can then be run by pressing CTRL+F11 or clicking Run in the top navigation bar. You can run all tests at once by highlighting the tests package in the package explorer and then running, or you can run one test at a time by highlighting the desired JUnit test and running it.

##Running Tests via Command Line in Windows
In order to run the JUnit tests via command line, you will first have to download JUnit. That can be done here: https://sourceforge.net/projects/junit/files/junit/4.10/

After downloading the JUnit .jar, place it in whichever directory you would like. One example would be C:\JUnit. After placing the .jar here, open your command prompt and set the JUnit environment. If your JUnit directory was C:\JUNIT, this can be done by the following command:

set JUNIT_HOME=C:\JUNIT

Replace C:\JUNIT with the directory you chose earlier if needed. Next, set the CLASSPATH variable by using the following command: 

set CLASSPATH=%CLASSPATH%;%JUNIT_HOME%\junit-4.10.jar;.;

Now, move the test files to the entities directory of our project. Navigate to the src directory. Compile the code by running the following command:

javac entities/*.java

Now run the desired test file by changing the name of the designated test file in the TestRunner.java class. This is found in line 9. It is pointing to AnimalTest.class by default. You may have to recompile the TestRunner.java file each time something is changed in the .java file. In order to run the desired test, use the following command:

java entities/TestRunner

More information, like how to run these tests in Linux or Mac, can be found here:
https://www.tutorialspoint.com/junit/junit_environment_setup.htm
