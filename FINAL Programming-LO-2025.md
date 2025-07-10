# Welcome to the Learning Outcomes Evaluation

Dear students,

Welcome to this Learning Outcomes Evaluation session. The goal is to assess your understanding and mastery of the learning outcomes for this semester as evidenced by your work that was submitted on your personal git account. Remember to answer each question thoroughly by referencing **Java** code and provide clear explanations where necessary.

Best regards,
Kay Berkling

## Ethics Section regarding generative and other forms of AI

The student acknowledges and agrees that the use of AI is strictly prohibited during this evaluation. By submitting this report, the student affirms that they have completed the form independently and without the assistance of any AI technologies. This agreement serves to ensure the integrity and authenticity of the students work, as well as their understanding of the learning outcomes.


## Checklist before handing in your work

* [ ] Review the assignment requirements to ensure you have completed all the necessary tasks.
* [ ] Double-check your links and make sure that links lead to where you intended. Each answer should have links to work done by you in your own git repository. (Exception is Speed Coding)
* [ ] Make sure you have at least 10 references to your project code (This is important evidence to prove that your project is substantial enough to support the learning outcome of object oriented design and coding within a larger piece of code.)
* [ ] Include comments to explain your referenced code and why it supports the learning outcome.
* [ ] Commit and push this markup file to your personal git repository and hand in the link and a soft-copy via email at the end of the designated time period.

Remember, this checklist is not exhaustive, but it should help you ensure that your work is complete, well-structured, and meets the required standards.

Good luck with your evaluation!

# Project Description (70%)

## [Links](https://www.notion.so/Programming-Project-22ba2f4637f480739880dc0a15eb117e?source=copy_link)

## [Message Broker](https://message-broker.atai-mamytov.click/login)

Message Broker v2 is an advanced web application built with Spring Boot, designed to automate the generation of insightful GitHub discussion comments based on commit and push events. Leveraging the GitHub API and an external AI microservice (e.g., Gemini), this application enhances developer productivity by creating context-aware discussions. An upgrade from the single-user Broker.v1, v2 supports multiple users, customizable prompts, and a user-friendly web interface.

Link to project: [Message-Broker-v2](https://github.com/NewStudy2024/message-broker-v2)

Link to project from 1st Semestr: [Message-Broker-v1](https://github.com/NewStudy2024/message-broker-atai)

## TECH STACK

* Java - Spring Boot -> core of service
* Docker, Docker Compose -> deployment and containerization
* Python for [microservice](https://github.com/NewStudy2024/gem-ai-client-atai)
* Nginx -> for my server

## What did you achieve?

The service, which:
- Supports multiple users with individual accounts.
- Allows creation of unlimited apps per user with custom prompts.
- Integrates with the GitHub API to fetch commit and push event data.
- Processes data via an external AI microservice to generate meaningful comments.
- Provides a web interface for user management and app configuration.
- Includes clear instructions and comprehensive testing (unit and integration).
- Ensures secure authentication and session management with Spring Security.

## Learning Outcomes

| Exam Question | Total Achievable Points | Points Reached During Grading |
|---------------|------------------------|-------------------------------|
| Q1. Algorithms    |           4            |                               |
| Q2.Data types    |           4            |                               |
| Q3.Complex Data Structures |  4            |                               |
| Q4.Concepts of OOP |          6            |                               |
| Q5.OO Design     |           6            |                               |
| Q6.Testing       |           3            |                               |
| Q7.Operator/Method Overloading | 6 |                               |
| Q8.Templates/Generics |       4            |                               |
| Q9.Class libraries |          4            |                               |


## Evaluation Questions

Please answer the following questions to the best of your ability to show your understanding of the learning outcomes. Please provide examples from your project code to support your answers.


## Evaluation Material


### Q1. Algorithms

Algorithms are manyfold and Java can be used to program these. Examples are sorting or search strategies but also mathematical calculations. Please refer to **two** areas in either your regular coding practice (for example from Semester 1) or within your project, where you have coded an algorithm. Do not make reference to code written for other classes, like theoretical informatics.

So, as a part of the self-study, during the first semester I implemented different Algorithms for understanding Java syntaxes.
Here some examples:
* [Binary Search](https://github.com/coffee3333/java-snippets/blob/main/BinarySearch.java)
* [Factorial](https://github.com/coffee3333/java-snippets/blob/main/Fibonacci.java)
* [Merge Sort](https://github.com/coffee3333/java-snippets/blob/main/MergeSort.java)
* [Reverse String](https://github.com/coffee3333/java-snippets/blob/main/ReviseStrings.java)
* [Checking Sort](https://github.com/coffee3333/java-snippets/blob/main/CheckSorted.java)
* [Fibo](https://github.com/coffee3333/java-snippets/blob/main/Fibonacci.java)
* [Finding MAX](https://github.com/coffee3333/java-snippets/blob/main/FindMax.java)
  Also, I've tested how my Discussion Bot (v1) worked, discussions [here](https://github.com/coffee3333/java-snippets/discussions)

Apart from that, I've used parsing technique for parsing JSON to Map(String, Object) [here](https://github.com/NewStudy2024/message-broker-atai/blob/main/src/main/java/app/v1/messagebroker/service/github/GitService.java).
Here in new version of Message-Broker (v2) I used parsing method, in order to parse the queryset to String, from line 69 [here](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/controller/AppController.java)


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4            |                               |


### Q2. Data types

Please explain the concept of data types and provide examples of different data types in Java.
typical data types in java are int, double, float, char, boolean, long, short, byte, String, and arrays. Please provide one example for each of the **four** following data types in your code.
* arrays
* Strings
* boolean
* your choice

[Here](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/model/App.java), you can find a model, which has different attributes, such as Name -> (String), id -> (Long), GitHub Token -> (String), this code considers as a Data Structure for an Entity in DB, that means it will appear in next section.
[Here](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/model/User.java) is another example of Entity, where you can find in line 29 a List<App> -> app, which keeps a different entities of app, and it should be a List.
Those are a simple examples, mostly in my projects I've used the Complex Data Structures.

Another examples with simple Data types:
* With arrays: [CheckSorted](https://github.com/coffee3333/java-snippets/blob/main/CheckSorted.java) in line 10, 11
* With Json: [JsontoString](https://github.com/coffee3333/java-snippets/blob/main/JsonToJava.java)

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4             |                               |


### Q3. Complex Data Structures

Examples of complex data structures in java are ArrayList, HashMap, HashSet, LinkedList, and TreeMap. Please provide an example of how you have used **two** of these complex data structures in your code and explain why you have chosen these data structures.

Example with Map -> [Here](https://github.com/NewStudy2024/message-broker-atai/blob/main/src/main/java/app/v1/messagebroker/service/github/GitService.java) in line 42, I receive a response from GitCommit Service, which consist of different data, such as code, changes, profile information, commiter. And from this huge amount of data i need to get valuable information, and our target is the difference of code, that I process afterward in gitMapperService.

Example with List -> [prev. example from Data types](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/model/User.java)

Example of another List -> [GitMapper Service](https://github.com/NewStudy2024/message-broker-atai/blob/main/src/main/java/app/v1/messagebroker/service/github/GitMapperService.java) in line 17, as mentioned before in prev. examples I need to get valuable Data, which is ["filename", "changes", "patch"], we parse data from Map - String

Data Transfer Object DTO which is also considered as Data Structure, and utilized mostly in Spring Boot as a Transfer object, [here](https://github.com/NewStudy2024/message-broker-atai/blob/main/src/main/java/app/v1/messagebroker/DTO/CloudFlareDto.java) is example


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           4             |                               |


### Q4. Concepts of OOP
Concepts of OOP are the basic building blocks of object-oriented programming, such as classes, objects, methods, and attributes.
Explain HOW and WHY your **project** demonstrates the use of OOP by using all of the following concepts:
* Classes/Objects
* Methods
* Attributes
  Link to the code in your project that demonstrates what you have explained above.

Since I implemented web service on Spring Boot, it already covers on of the pillars like: Polymorphism, because Beans implemented as interfaces which is on top of the Abstract classes.

However, here some links for OOP:
* [Encapsulation](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/model/App.java), [another](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/model/User.java)
    * Explanations: All attributes are isolated(private) and using library lombok, it generates all getter and setters
* [Polymorphism and Inheritance](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/repository/AppRepository.java)
    * Explanations: this is a repository layer, and here we see combination
* [Polymorphism](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/controller/CustomErrorController.java), [also here](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/model/User.java)
    * Explanations: As u can se here we are implementing different interfaces in order to provide config for Spring Boot
* [Methods](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/controller/AppController.java)
    * Explanations: Technically from framework view, those are an endpoints, but from OOP side a methods, which are exec. when there is an event on endpoint
* [Attributes again from encapsulation](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/model/App.java), [another](https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/model/User.java)


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|             6           |                               |

### Q5. OO Design
Please showcase **two** areas where you have used object orientation and explain the advantage that object oriented code brings to the application or the problem that your code is addressing.
Examples in java of good oo design are encapsulation, inheritance, polymorphism, and abstraction.

So, as was mentioned before Spring build on OOP and on MVC Pattern
Where we have all concepts OOP, which were demeaned before in Concepts OOP

##### MVC
  * Models : https://github.com/NewStudy2024/message-broker-v2/tree/main/src/main/java/app/v2/messagebroker/model
    * Also repository part : https://github.com/NewStudy2024/message-broker-v2/tree/main/src/main/java/app/v2/messagebroker/repository
  * Controllers : https://github.com/NewStudy2024/message-broker-v2/tree/main/src/main/java/app/v2/messagebroker/controller
  * Views inside controllers
  * Business logic services : https://github.com/NewStudy2024/message-broker-v2/tree/main/src/main/java/app/v2/messagebroker/service

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|              6          |                               |



### Q6. Testing
Java code is tested by using JUnit. Please explain how you have used JUnit in your project and provide a link to the code where you have used JUnit. Links do not have to refer to your project and can refer to your practice code. If you tested without JUnit, please explain how you tested your code.
Be detailed about what you are testing and how you argue for your test cases.

Feel free to refer to the vibe coding session where you explored testing. (pair programming - you may link to your partner git and name him/her.)

Test cases usually cover the following areas:
* boundary cases
* normal cases
* error cases / catching exceptions

Testing for all project here : https://github.com/NewStudy2024/message-broker-v2/tree/main/src/test/java/app/v2/messagebroker


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|         3               |                               |

### Q7. Operator/Method Overloading
An example of operator overloading is the "+" operator that can be used to add two numbers or concatenate two strings. An example of method overloading is having two methods with the same name but different parameters. Please provide an example of how you have used operator or method overloading in your code and explain why you have chosen this method of coding.
The link does not have to be to your project and can be to your practice code.

Again since it is Spring Project, all Beans are overloading methods

Example from project: @Controller, @Model and etc.
https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/controller/AppController.java

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|          6              |                               |



### Q8. Templates/Generics
Generics in java are used to create classes, interfaces, and methods that operate on objects of specified types. Please provide an example of how you have used generics in your code and explain why you have chosen to use generics. The link does not have to be to your project and can be to your practice code.

Interfaces: https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/repository/AppRepository.java
Implementation of interface : https://github.com/NewStudy2024/message-broker-v2/blob/main/src/main/java/app/v2/messagebroker/controller/CustomErrorController.java

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           6             |                               |

### Q9. Class Libraries
Examples of class libraries in java are the Java Standard Library, JavaFX, Apache Commons, JUnit, Log4j, Jackson, Guava, Joda-Time, Hibernate, Spring, Maven, and many more. Please provide an example of how you have used a class library in your **project** code and explain why you have chosen to use this class library.

POM.xml where all links : https://github.com/NewStudy2024/message-broker-v2/blob/main/pom.xml
lombok, postgresql, spring-boot-starter-web, spring-boot-starter-security, thymeleaf-extras-springsecurity6, springdoc-openapi-starter-webmvc-ui, junit-jupiter

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            6            |                               |


# Creativity (10%)
Which one did you choose:

* [ ] Web Interface with Design
* [ ] Database Connected
* [ ] Multithreading
* [ ] File I/O
* [ ] API
* [ ] Deployment

I implemented :
  * API
  * Deployment
  * Database Connected
  * Web Interface with Design

https://message-broker.atai-mamytov.click/login

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            10          |                               |



# Speed Coding (20%)
Please enter **three** Links to your speed coding session GITs and name your partner.


- Week2 - [Grade management project](https://github.com/NewStudy2024/programming-second-semestr-atai/tree/main/src/main/java/app/v1/week2) - Partner "Sahil"
- Week3 - [Car rental project](https://github.com/NewStudy2024/programming-second-semestr-atai/tree/main/src/main/java/app/v1/week3) - Partner "Daniel"
- Week4 - [Inventory management system project](https://github.com/NewStudy2024/programming-second-semestr-atai/tree/main/src/main/java/app/v1/week4) - Partner "Niki"
- Week5 - [Vibe Coding](https://github.com/NewStudy2024/programming-second-semestr-atai/tree/main/src/test/java/v1)

### OOP Pillars Links
- [Encapsulation](https://github.com/NewStudy2024/programming-second-semestr-atai/blob/main/src/main/java/app/v1/week2/Subject.java)
- [Inheritance, Polymorphism](https://github.com/NewStudy2024/programming-second-semestr-atai/blob/main/src/main/java/app/v1/week4/HomePageServlet.java)

Paste your class diagram for your project that you developed during the peer review class here:

https://eu02web.zoom.us/wb/doc/BXz7ouidQoqR3HKB_x7ZTg/p/161424972578816?moveToTarget=163774291650616
https://eu02web.zoom.us/wb/doc/BXz7ouidQoqR3HKB_x7ZTg/p/161424972578816?moveToTarget=102248327901348
https://eu02web.zoom.us/wb/doc/BXz7ouidQoqR3HKB_x7ZTg/p/161424972578816?moveToTarget=51615531411886

It can be done very simply by just copying any image and pasting it while editing Readme.md.


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            16            |                               |

