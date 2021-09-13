#  Kafein Case Study

## About
This project is prepared for "Kafein". During the project, a structure where users parking their vehicles was modeled.

## Requirements and Tech Stack

Java 11, Spring, Spring Boot, Hibernate, JUnit and Intellij or Eclipse for development and run.

## API Details
Project has 1 controller and it's name is ParkingLotController.

#### Parking Lot API
In Customer controller we can create new customer, get customer by given id and get customers all orders by given customer id.

Method: Post, URL: "/api/parking-lot/park/vehicle" -> Park the vehicle

Method: Delete, URL: "/api/parking-lot/park/leave/{SLOT_NUMBER}" -> Lave the park

Method: Get, URL: "/api/parking-lot/park/status" -> Get parking lot status


## Download and Running Application

You can download this project with two way:
1) Click code and copy link then paste terminal  git clone https://github.com/batuhanosman/Kafein-Parking-Automation.git

2) Click code and download as JAR.
Note: Practical way to start this app is right click the main class and run as java application. If you want to make executable jar please follow the steps below.

After get project cd to project folder then open terminal here and run the
```mvn clean package```
command. If you are using STS/Eclipse IDE, then Right Click on your project » Run As » Maven build… » Goals:
```clean package```
» Run. This will build our project and running tests after that create an executable JAR file of application and put it within the "target" folder.

For run application with jar, run this command in terminal;

```java -jar target/parkingautomation-0.0.1-SNAPSHOT.jar```

## Testing Application

This application has a swagger implementation for testing.
Just go to [Swagger Link](http://localhost:8999/swagger-ui.html#/) after run the app.


## Postman Collection
You can use this link to copy collections of API's -> https://www.getpostman.com/collections/8b0d1695dcf62f1152c6
