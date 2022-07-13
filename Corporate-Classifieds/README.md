# Corporate-Classifieds
Steps
1) add the sqlfiles/requiredTables.sql to the mysql workbench and run it.
2) change the sql username and password in application.properties to configure according to your sql database.
3) run each microeservice.
4) ng serve to run the angular application


** to check rest api for each microservice **
1) auth - localhost:8080/authapp/swagger-ui.html
2) points - localhost:8090/points/swagger-ui.html
3) employee - localhost:8070/swagger-ui.html
4) offer - localhost:8000/swagger-ui.html
5) classified portal - http://localhost:4200
