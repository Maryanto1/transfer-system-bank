# transfer-system-bank

This is a sample Java / Maven / Spring Boot (version 3.3.0) application that can be used as a 
starter for creating a service transfer internal between accounts and internal bank.

# How-to-run
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

1. Clone this repository
2. Make sure you are using JDK 17 and Maven 3.8 or above
3. Installing redis
4. Installing postgresql
5. Adjust application.properties file based on redis and postgresql credentials

To see the documentation for using the api, you can access
http://localhost:8080/swagger-ui/index.html#/transfer-controller/doInquiry


