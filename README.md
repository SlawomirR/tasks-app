Tasks Application
=================
Information
-----------
This project do simple task list management.\
It is design as **REST API** and has been deploy to **Heroku** service: [https://mysterious-dusk-98078.herokuapp.com](https://mysterious-dusk-98078.herokuapp.com).\
It uses **Trello** service to deal with tasks as cards that can be checked at: [https://trello.com/b/r3PFBoTh](https://trello.com/b/r3PFBoTh).\
Tasks are stored to **Heroku's PostgreSQL** database through the API using standard **CRUD** operations.\
Front-end to test the functionality can be found at: [https://slawomirr.github.io/tasks-app_front-end](https://slawomirr.github.io/tasks-app_front-end).\
This repository is a local version of **the source code** (back-end) that was deployed to Heroku.

Run Locally
-----------
Running application locally impose **you have MySQL server installed** and run with **created database named "database_name"**. User and password to access database should be setup to "**database_user**" and "**database_password**".\
Those settings you can change in: "**src/main/resources/application.properties**" file in folder you cloned the repository.

1. Make local copy of the repository:
    1. clone the repository with: "git clone [https://github.com/SlawomirR/tasks-app.git](https://github.com/SlawomirR/tasks-app.git)", or
    1. download the compressed archive: "[https://github.com/SlawomirR/tasks-app/archive/master.zip](https://github.com/SlawomirR/tasks-app/archive/master.zip)"
2. Setup your MySQl server properly or edit projects settings as mentioned above
3. Run: "**./gradlew bootRun**" or "**gradlew.bat bootRun**" (depending on your OS) in the main folder of the project
4. Visit: "[http://localhost:8085](http://localhost:8085)" with your web browser if you left port number unchanged
