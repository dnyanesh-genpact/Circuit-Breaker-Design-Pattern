# Circuit-Breaker-Design-Pattern
The Circuit Breaker Design Pattern of Microservices Architecture.

# Problem that Circuit Breaker Design Pattern solves
In a distributed system, service failures or unresponsive components can occur. If a calling service continues to make requests to a failing or slow service, it can lead to degraded performance or complete system failures.

# Solution for this problem 
The Circuit Breaker pattern provides a mechanism to detect and handle faults by tripping the circuit, preventing further requests to the failing service. This promotes fault tolerance and prevents the system from overloading or becoming unresponsive due to repeated failures.

# Prerequisites
  - Java Development Kit (JDK 8 or above)
  - Maven
  - IDE like STS(Spring Tool Suite) or Eclipse

# Architecture Overview
We will create 3 microservices : 
  - Account Service -> Manages Bank Account creation and fetching
  - Fund Transfer Service -> Manages trasnfer of funds for accounts
  - Bank Rates Service -> Will give cross rates to Fund Transfer Service before it processes transactions.

# Steps : 
  1. Create Microservices :
      Create three separate Spring Boot projects (either via Spring Initializr or your preferred method). 

  2. Add Dependencies :
      For Bank Rate Service, add just web dependency :
     ![image](https://github.com/dnyanesh-genpact/Aggregator-Design-Pattern/assets/152908296/00294a58-3a08-48e3-8217-60ef1a7bd454)

      For Fund Transfer service, add following dependencies :
     ![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/1d7c0265-4009-4e4b-8f5e-159bba1df2cf)


  4. Business Logic :
       1) Account Service - Create a REST API to get an account by account number.
       2) Fund Transfer Service - Create a REST API to post fund transfer transaction by calling Bank Rate service for rate calculcation.
       3) Bank Rate Service - Create a REST API to call post cross rates requried for fund transfer.

  5. Bank Rate Service purpose and logic : 
      Fund Transfer Service, before processing any transaction for particular account, will call Bank Rates Service in order to get the cross rates for fund processing based on currencies.
      If, from Currency and to Currency is equal, the cross rate would be 1. Otherwise, the cross rate would be 10.345. The amount that is being used processed for transfer will be updated with cross rate amount.


     Here's the logic :

     ![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/062e77c8-eae2-4611-80ed-a927423e3e27)


  7. Run the services : 
      Start all 3 services i.e. Account Service, Fund Transfer Service and Bank Rate Service.

  8. For testing : 
     - Hit the Actuator URL with Fund Transfer Service port 5052 to check if Fund Transfer service is successfully able to call Bank Rate Service. The state of Bank Rates service should be CLOSED with status as 
       UP.
        http://localhost:5052/actuator/health

     - Make a few calls to Fund Transfer Service to ensure if interservice communication is working fine.

     - Now, stop the Bank Rates Service.

     - Make 3 POST calls on Fund Transfer Service and check with actuator URL if Circuit is Half Open. Since the number of failure calls are exceeding the threshold set for given number of minimum calls, the circuit will go to Half Open state.

     - Make 3 calls again in Half Open State to check if Bank Rates Service is up or not.

     - Since the service is not up and maximum calls allowed in Half Open State are exhausted, the actuator health URL will show that circuit is OPEN and Bank Rates Service is not responding.




# Microservices Configuration
Customize the behavior of services by editing the respective application.properties file. Adjust settings such as port, logging, and error handling.

Account Service : 

![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/a88836f1-20e3-442a-ba89-f05c7bf52a1c)


Circuit Breaker configuration for Fund Transfer Service application.properties 
![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/9fe70cbb-b865-47dc-b007-b6082d7ebafb)


Bank Rates Service : 

![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/d2f2a947-6417-4d7e-b7e7-f9c1539031cf)



# Sample Code for Controllers and Models

**FundTransfer Controller** : 

![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/7689b76d-891a-4523-89bd-a9639c369557)



**FundTransfer model** : 

![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/212e5742-47d4-49ff-aa2c-84400d976a17)

![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/7a1810df-d80f-4d34-922e-17222e82ec7d)



**Bank Rates Controller** : 

![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/955ad0e5-454a-4fba-9a84-f402008fcffa)



**Bank Rates Model** : 

![image](https://github.com/dnyanesh-genpact/Circuit-Breaker-Design-Pattern/assets/152908296/ee906e52-a98d-4ed3-98a8-3bb251c8fb42)


# Contributing
  Contributions are welcome!

# Contact
  For questions or feedback, please email at tathoded@gmail.com OR dnyaneshsunilrao.tathode@genpact.com.



   
     
      

  

