# nokia_assignment

The objective of this Java application, which operates through command line interface, is to execute specific database transactions related to a Company's pursuit 
of purchasing Parts from a diverse group of Manufacturers. The application is equipped with a user-friendly Menu system, which facilitates navigation and execution of operations.


*** Run the project *** (Required steps are mentioned below)

1. Setup jdk 17 in your system
2. Setup MySQL Database(checkout steps in section "Setup MYSQL Database")
2. Clone repository
3. Rebuild project
4. Run MainApp.java file


*** Setup MySQL Database ***

1. Download and install MySQL server
2. Download and install MySQL Workbench(It's a MySQL client,any other cliet app can' be also used)
3. Configure the MYSQL Server in MySQLWorkbench 
4. Use following params 
		-- Port 3306
		-- User - root 
		-- Password - 123456no

5. Create database schema called "nokiadb"

Note :- In case of connection related issue verify the DB configuration in "hibernate.cfg.xml"



*** Database Schema ***


We have defined 5 entities -

Company
CompanyStock
Manufacturer
Part
PartManufacturer



*** Technologies Used ***

Language Used - Java
Dependency Manager - Maven
Database used - MYSQL (Version 8.0.32)
ORM - Hibernate



*** Architecture ***


--- Menu System


This Project Consists abstract class "Menu" which has abstract methods to display the menu, take user input, and handling operations based on user input.

First Object of subclass "MainMenu" of reference type "Menu'" gets created.During object creation, List of submenus would be set to handle the menu flow.

Then display method will be called to display the Main Menu.

Then getUserChoice method gets executed and returns user input/choice.

After that handleUserChoice(int choice) method gets called to perform further action/operation based on user choice.

Similarly it will keep executing the inner flows of subclasses based on user input.



*** Test Cases ***

Test cases are covered for 2 important business layer classses 
