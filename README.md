# sbhcp-sample
 
 This projects contains a SAP Cloud HANA HCP Sample application using 
 
 * Java 8
 * Spring Boot/ Spring Data against a SAP HANA DB
 
 We're using a simple multi maven approach to support for code base growth
  
 ## Requirements to run the application
 
* Java 8
* Maven

## Modules

* model (the DB entities)
* core (services and repositories)
* web (rest interface)

## Running the application

It's important to have a look at the properties for each environment:

sbhcp-sample/web/src/main/resources

### Local mode

No need to use any SAP powered server, a plain tomcat will work. Just run:

<p>sbhcp-sample> mvn clean install</p>
<p>sbhcp-sample/web> mvn spring-boot:run -DSPRING_PROFILES_ACTIVE=local</p>

This last starts an embedded tomcat server:

http://localhost:8080 to access see the swagger API

### HCP model

<p>sbhcp-sample> mvn -P neo clean package install<p>

Then deploy the target/ROOT.war into HCP (don't forget to add the env parameter with the environment:
 
<p>-DSPRING_PROFILES_ACTIVE=dev or</p>
<p>-DSPRING_PROFILES_ACTIVE=pro</p>

