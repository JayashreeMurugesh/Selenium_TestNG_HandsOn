# Selenium_TestNG_HandsOn
Automated four sample testcases of OrangeHRM Demo web application.
This is a simple project that would allow anyone to get up and running with Java, Maven, Selenium and TestNG. It also explains how to execute a simple test that will assert the title of a page

Pre-Requisite:
Java Installation / Update
Setting up Eclipse
Installing TestNG

Java Installation / Update
Check your system to see if you have the latest Java version installed.

Command:

$ java -version
If you do not have the latest Java installed, find out how to install Java here.

Ensure your JAVA_HOME environment to the location of the installed JDK. Find out how to do that here.

Setting up Eclipse:
Navigate to the Eclipse download page and download eclipse for Java EE developers.
Installing TestNG Plugin-Inside Eclipse, click on the Help menu
Select Install New Software -> Search Testng and install
Setting up Maven:
Download Maven here https://maven.apache.org/download.cgi
Maven Installation
Unzip the distribution archive to the directory you wish to install Maven. I extracted maven to my Documents folder
Add Maven to the PATH. More information can be found in the README.txt in the zip folder.

Here's an example of how I added Maven to my PATH
<img width="436" alt="image" src="https://github.com/user-attachments/assets/e1f2bdf3-d78c-4e4f-a792-b574630ac296">
Command:
<img width="656" alt="image" src="https://github.com/user-attachments/assets/b7615f39-338e-4d6a-96f1-0cb84878694a">

Following the above steps executes the configurations found in the testng.xml file by default.

From the Command Line
Navigate to the location of project E:\Selenium with Java\Projects\OrangeHRM_Demo>
Then type E:\Selenium with Java\Projects\OrangeHRM_Demo>mvn validate
<img width="575" alt="image" src="https://github.com/user-attachments/assets/a0e535f0-4131-45e4-808d-17d215ef7f31">
Then type E:\Selenium with Java\Projects\OrangeHRM_Demo>mvn compile
<img width="796" alt="image" src="https://github.com/user-attachments/assets/bae4f90c-6d69-4700-a5cd-0b2c9140ab9a">
Then type E:\Selenium with Java\Projects\OrangeHRM_Demo>mvn test
//This command will execute all the tests in the project
