
### Homework Case
Before test run please run command below :

 **mvn clean install -DskipTests**
#
run cases

**mvn test**




### Challenge 1 - UI ###

Based on the search functionality of the Amazon website describe test scenarios using equivalence class partitioning (ECP) to assure that everything is working as it is expected for an e-commerce web page. After that, choose an automation framework and implement the described test scenarios, you can use any language or framework.

* https://www.amazon.com

### Challenge 2 - API ###

You need to test a users API that can register users through a POST method, with the following information:

* id
* UserName
* password

API Swagger documentation:

* http://fakerestapi.azurewebsites.net/swagger/ui/index#!/Users/Users_Post

Post:

* http://fakerestapi.azurewebsites.net/api/users
 
The API can also retrieve users by id using a GET method:

* http://fakerestapi.azurewebsites.net/api/users/{id}

Requirements:

* System must not permit duplicate users (ID should be unique)
* New users should be able to register
* System should be able to retrieve registered users information
* An error should be displayed when trying to retrieve a user that was not yet registered (404). 

Describe test scenarios for the given requirements using ECP, after that, choose an automation framework and implement the described test scenarios, you can use any language or framework. 


## Make a fork of this repository and submit a pull request with your solutions! Good luck! ##


**The use of automation design patters will be a differential.**

**Please, include in the README all the information needed to run the project!**
