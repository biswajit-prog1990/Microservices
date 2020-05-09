# Microservices 
------------------------------------------------------------------------------------------------
# Project1- Online Library Management System
------------------------------------------------------------------------------------------------
• Created a Library Management System, and incorporated Microservices Architecture
• Used Spring Data JPA with Hibernate for sending data to backend system PostgreSQL
• Created Discovery Client using Netflix Eureka Server, and added Zuul API Gateway to the Discovery Client
• Incorporated Login mechanism using username password authentication to the Login Microservice. Once the user is authenticated, a JWT token is generated with a specific expiration and it is checked before sending request to other rest controllers
• Used Spring Cloud Server for centralized configuration of application properties and added it to a cloud Github private repository. Used Spring Cloud Bus and rabbit MQ for Bus refresh functionality
• Used Java Cryptography Extension for implementing Asymmetric cryptography algorithm in Spring Cloud Server
• Used Feign Client for communication between different Microservices and used Hystrix for implementing Fallback functionality for Microservices
• Used Sleuth and Zipkin for Distributed tracing using Span Id and Trace Id
• Implemented ELK (Log Stash, Elastic Search, Kibana) for centralized monitoring of application logs

------------------------------------------------------------------------------------------------
# Important Links
------------------------------------
http://localhost:8011/users-ms/users/status/check -> For status checks (You need to provide Authentication Bearer JWT Token before using the link)
http://localhost:8011/users-ms/users -> User Registration
JSON -  {
	"firstName":"",
	"lastName":"",
	"email":"",
	"password":""
}
http://localhost:8011/users-ms/users/login -> User Login
JSON - {
	"email":"",
	"password":""
}
The password to be provided as Plain Text password which will be updated to Encrypted and stored in Backend DB and during login the Encrypted password will be auto decrypted and user id will be fetched
http://localhost:8012/actuator/bus-refresh -> For bus refresh from Rabbit MQ using Spring Cloud Bus