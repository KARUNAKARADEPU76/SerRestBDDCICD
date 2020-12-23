#### **Swagger Petstore API Tests (Cucumber - Rest Assured)**


**The starter project**

The best place to start with Serenihttps://github.com/serenity-bdd/serenity-cucumber-starterty and Cucumber is to clone or download the starter project on Github ([https://github.com/KARUNAKARADEPU76/SerRestBDDCICD]). This project gives you a basic project setup, along with some sample tests and supporting classes.
This is automation suite is designed to test the Swagger Pet store POST /pet request.

The project has 12 scenarios that that can be located within the feature files:

src/test/resources/OrderTests.feature
src/test/resources/PetTests.feature
src/test/resources/UserTests.feature
OrderTests.feature scenarios include: Scenario: Correct error message is provided when searching for an order that does not exist. Scenario: Users are able to place orders for pets Scenario: Users are able to search for orders by its id Scenario: Users are able to place orders for available pets Scenario: Users are NOT able to place orders for available pets (fails within test suite, this is error in the API rather than the automation suite assuming users should not be able to create orders for pets that are not available).

PetTests.feature scenarios include: Scenario: Users are able to add pets to the system Scenario: Users are able to delete pets from the system Scenario: Users are able to add pets that have multiple tags Scenario: Users are able to add pets that have no tags Scenario: Id's are automatically generated for pets when not supplied with the request Scenario: Adding a pet with no body for the request results in a 400 response

UserTests.feature scenarios include: Scenario: Users are searchable by their username

Important Note: scenarios need to have the tag @smokeTest in order to be included in test executions

You will need:
- Java 1.8+ installed (Does not work with Java below 1.8) [I ran it on JDK 8 as well]
- Maven Installed (I use version 3.8.0)
- IntelliJ (Or another Java IDE)
#**Note**: This suite has only been tested on a Mac. If possible please use a Mac to execute the test suite

In order to execute the automation suite navigate to the Project directory within a Terminal and run the command: 'mvn clean test' or 'mvn clean verify'. OR 'gradle clean test'

12 Scenarios will be executed. Report file can be found 'target/reports/test-report/index.html' 5 of the 12 test scenarios 
(Correct error message is provided when searching for an order that does not exist, Users are able to place orders for available pets, Users are NOT able to place orders for available pets, Users are able to delete pets from the system, Adding a pet with no body for the request results in a 400 response) These tests are coded correctly, this is a legitimate failure when using the assumption that users should not be able to place orders for pets that are not available.


###### **Jenkins integration:**

I have build this project successfully on Jenkins and verified the test results by publishing the 'Publish Serenity Report' in the Post Build Actions and also i used 'Publish Thucydides reports' to view the test results.

To build and deploy the above project, go to Jenkins and create a Maven Project, then configure the project with below details
Section: Source code management
Add the Repository URL as "**[https://github.com/KARUNAKARADEPU76/SerRestBDDCICD]**" here the url is my Github url, you have to upload to your Git Repository and then have to use that to execute.
 then in Branch Specifier (blank for 'any'), you can choose ***/master**. leave all other fields default selections and go to the section **[Post-build Actions]** and select the [Publish Thucydides reports] (you can install the plugin, if not available)for reporting purpose and save the Build configuration. 
 then build the project. click on the link 'Thucydides Test Report' to view th etest results.