This JavaFX project has been enhanced to integrate JDBC and use the H2 relational database for storing data. 
The project comprises a utility class (DatabaseUtils) responsible for managing categories in the H2 database.

Technologies Used
JavaFX: Used for building the graphical user interface.
Maven: Project management and build tool.
JUnit: Testing framework for unit testing.
JDBC: Java Database Connectivity for interaction with the H2 database.
H2 Database: Embedded relational database engine.

How to use:
Ensure you have the H2 Database installed.
Enter the JDBC URL, username, and password from conf/database.properties in the H2 web console login page.
The database connection details are stored in the conf/database.properties file. Make sure to configure this file with the appropriate database URL, username, and password before running the application.

Add Controllers:
Users can add new entities.
Clicking the "Add" button triggers the insertion of a new entity into the H2 database.

Search Controllers:
Users can search for entities based on ID.
The search results are displayed in a TableView.

Database Interaction
The DatabaseUtils class provides methods for connecting to the H2 database, inserting new entities, retrieving all entities, and filtering entities based on specific criteria.
