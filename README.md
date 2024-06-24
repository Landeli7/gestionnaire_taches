# gestionnaire_taches
Technical test : Task management app

This application was developed with Java / Spring boot, Maven and Angular with a MySQL database.

The following features are available :
- Basic log in and sign up.
- Create a task with a title, a description, a due date and a priority.
- Displaying the tasks of a user and their details.
- Sort the tasks of a user by due date.
- Set a task as "completed" or "not completed".
- Modify the priority of an existing task.
- Delete a task.

This application can be accessed in several ways.

### Back-end API

The Back-end Spring boot API is available at localhost:3000. In this project you will find a postman collection to help you test the requests to the application.

### Shell

You can use the shell which is accessible when starting the back-end.
To list the available commands, you can type this :

> help

For example, if you want to create a new task you can type the following :

> add-task --userId 1 --name "testTask" --priority 4 --desc "This is a description" --dueDate 2024-12-31


### Internet browser

The application is available by using your internet browser at localhost:4200 :

![Login page](/images/login_page.png)

You must log in (or create an account and then log in) to get access to the app with the internet browser.

### Database

I have used a Wamp Server with Mysql Workbench to get access to the MySQL database.
You will find an SQL dump of the database with test data if you need it.

## Technical choices

I have decided to create the application with Java / Spring boot and Angular to demonstrate my skills with these specific technologies.
Also, I think this is convenient to test the back-end via an API.

There are two repositories : User and UserTask. They interact with the database.
The User repository is used by the AuthService for the log in and the sign up.
The UserTask repository is used by the TaskService to manage the tasks.

There are also interfaces to describe the AuthService and the TaskService.

I have created entities and DTOs to represent the data in the database and to set validators to verify that the data is stored using the right format on the database.

There are two controllers : AuthController and TaskController. They interact with the Angular client and the services.

Everything related to the shell is in the shell directory. It uses the TaskService to manage the tasks.

I have used recent versions of Java (version 17 LTS) and Angular (18) to stay up to date with the latest features and for security reasons.

## Starting the application

To start the application, you need to do the following steps.

To launch the back-end you can install Visual Studio Code with the Java and Maven extensions (by Microsoft). Then, you will be able to click on the arrow in the upper-right corner of your window to launch the back-end.

You can run this command to launch the front-end :

>ng serve