# todo-app-kmp-backend
The backend app of the todo-app-kotlin-multiplatform.

### Running

First of all, you must setup the following environment variables:

- `PORT` defines which port the application will run on
- `JDBC_DATABASE_URL` is the JDBC URL connection string for PostgreSQL
- `JDBC_DATABASE_USER` defines which user will be used to authenticate into PostgreSQL
- `JDBC_DATABASE_PASSWORD` represents the password of PostgreSQL user

#### Heroku

This app is also available on [Heroku](https://todo-app-kmp-backend.herokuapp.com/todos).

For Heroku deployments:

- `heroku login`
- `heroku plugins:install java`
- `./gradlew jar`
- `heroku deploy:jar build/libs/todo-app-kmp-backend.jar --app todo-app-kmp-backend`
