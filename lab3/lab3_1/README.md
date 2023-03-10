# LAB3_1

## 3.1 Employee manager example
Questions

### a) Identify a couple of examples that use AssertJ expressive methods chaining.
* On the class **B_EmployeeService_UnitTest**, on the **given3Employees_whengetAll_thenReturn3Records** test;
* On the class **C_EmployeeService_UnitTest.java**, on the **givenManyEmployees_whenGetEmployees_thenReturnJsonArray**test

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database). 
In the B_EmployeeService_UnitTest test class, we create a Mock EmployeeRepository and then we Inject this Mock in into to the EmployeeServiceImpl. 

### c) What is the difference between standard @Mock and @MockBean?
The Standart @Mock allow us to create a mock for an Object or Interface, we use this @Mock to enforce return values of certain calls. @MockBean is only use in the Spring application context. The mock will replace any existing bean of the same type in the application context if it exists, if not one will be created. 

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
Just the application.proprierties in the main/resorces directory is used to configure the application properties, like configuring the Spring Boot framework and defining our application custom configuration properties such as what Database to use and how to access it. The application-integrationtest.properties does the same for our tests. In this example it defines wjat database system to use as well as the username and password to such system

To use these properties we need to enable them by adding the following annotation to the test class
@TestPropertySource(locations = "application-integrationtest.properties")

### e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 
In the aproach C we Mock the Service and only test it on the Controller Layer.
On approach D we Mock the entire Spring Enviorment with a mock servlet environment.
On approach E we actually create a web application context.