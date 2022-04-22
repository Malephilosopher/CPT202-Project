# CPT202-Project
XJTLU Online Learning Resource Sharing Website

**Project Structure:**

```json
CPT202Project
|---src  //source codes folder
|   |---main
|   |   |---java
|   |   |   |---com.xjtlu.cpt202.cpt202Project
|   |   |   |   |---config  //configurations folder
|   |   |   |   |   |---WebConfig.java  //configuration class for interceptors and web
|   |   |   |   |---controller  //controller folder
|   |   |   |   |   |---BlogController.java  //controller for requests regarding to Blog entity
|   |   |   |   |   |---CommentController.java  //controller for requests regarding to Comment entity
|   |   |   |   |   |---UserController.java  //controller for requests regarding to User entity
|   |   |   |   |---entity  //Entity folder
|   |   |   |   |   |---Audience.java  //entity storing configurations for jwt
|   |   |   |   |   |---Blog.java  //entity storing information of a blog
|   |   |   |   |   |---Comment.java  //entity storing information of a comment
|   |   |   |   |   |---Result.java  //entity for conveying response information to the front end
|   |   |   |   |   |---User.java  //entity storing information of a user
|   |   |   |   |---exceptions  //customed exception for inner use
|   |   |   |   |   |---UserException.java  //exceptions about user
|   |   |   |   |   |---interceptor  //interceptors for intercepting requests
|   |   |   |   |   |---JwtInterceptor.java  //interceptor for token verification
|   |   |   |   |---mapper  //mapper interfaces folder, interact with database
|   |   |   |   |   |---BlogMapper.java  //mapper interface for Blog entity
|   |   |   |   |   |---CommentMapper.java  //mapper interface for Comment entity
|   |   |   |   |   |---UserMapper.java  //mapper interface for User entity
|   |   |   |   |---service  //service folder for methods containing service logics
|   |   |   |   |   |---Impl  //implementations of service interfaces,contains detailed service logic
|   |   |   |   |   |   |---BlogServiceImpl.java  //implementation of BlogService interface
|   |   |   |   |   |   |---CommentService.java  //implementation of CommentService interface
|   |   |   |   |   |   |---UserServiceImpl.java  //implementation of UserService interface
|   |   |   |   |   |---BlogService.java  //service interface for Blog entity, defines the basic structure
|   |   |   |   |   |---CommentService.java  //service interface for Comment entity
|   |   |   |   |   |---UserService.java  //service interface for User entity
|   |   |   |   |---util  //folder for utility classes
|   |   |   |   |   |---Base64Util.java  //utility functions for Base64 decryption and encryption
|   |   |   |   |   |---JwtUtil.java  //utility functions for token generation and verification
|   |   |   |   |---CPT202ProjectApplication.java  //startup class of the project
|   |   |---resources  //folder for static resources
|   |   |   |---mapper  //mybatis configuration folder
|   |   |   |   |---BlogMapper.xml  //mybatis configuration for Blog entity
|   |   |   |   |---CommentMapper.xml  //mybatis configuration for Comment entity
|   |   |   |   |---UserMapper.xml  //mybatis configuration for User entity
|   |   |   |---application.yml  //configuration file for the whole project
|   |---test  //folder for test functions
|---target  //folder for compiled class files and jar packages
|---pom.xml  //maven configuration file for managing dependencies
```

**Deployment Instruction:**

1. Create a database named CPT202 in your local mysql environment and Run the **CPT202.sql** sql file to initialize the database structure.

2. Open the **CPT202Project-backend** folder with your IDE (IDEA recommended). 

3. Open the **application.yml** configuration file under the **resources** folder.

4. Change the username, password and IP address of the data source to your own. (Redis config is not necessary at this version.)

5. There are two approaches to run the backend project. The first is to run the file **with IDEA**, which is a bit more complicated. You will need to open this project as a maven project and wait for IDEA to import all required dependencies. After the dependencies are imported, you should click the **running config** button on the top bar and check if the configurations are correct. Bear in mind that VM options are optional, and you can change the Heap size value to a quarter of your computer's memory size (e.g. 4096m for a 16G Memory size computer). Then you can run this project by clicking the run button on the top bar.

6. The second way to run our backend project is to open the **command line** on your computer and change disc to the project directory. We have already packaged a jar package for you in the **target** folder. You just need to change disc to the **target** folder and run the jar file with the command: java -jar CPT202Project-0.0.1-SNAPSHOT.jar.

7. In this way, if you shut down the command line, the program will automatically shut down too.

   To run this project at backstage, run the command line: nohup java -jar CPT202Project-0.0.1SNAPSHOT.jar & . The output of the program will automatically be written to a newly created nohup.out file.
