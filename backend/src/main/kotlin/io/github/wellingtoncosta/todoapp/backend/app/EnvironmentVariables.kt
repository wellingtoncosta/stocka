package io.github.wellingtoncosta.todoapp.backend.app

private const val JDBC_DATABASE_URL = "JDBC_DATABASE_URL"
private const val JDBC_DATABASE_USERNAME = "JDBC_DATABASE_USERNAME"
private const val JDBC_DATABASE_PASSWORD = "JDBC_DATABASE_PASSWORD"

object EnvironmentVariables {

    val jdbcDatabaseUrl: String = System.getenv(JDBC_DATABASE_URL) ?:
        throw IllegalStateException("Environment variable JDBC_DATABASE_URL is not present.")

    val jdbcDatabaseUsername: String = System.getenv(JDBC_DATABASE_USERNAME) ?:
        throw IllegalStateException("Environment variable JDBC_DATABASE_USERNAME is not present.")

    val jdbcDatabasePassword: String = System.getenv(JDBC_DATABASE_PASSWORD) ?:
        throw IllegalStateException("Environment variable JDBC_DATABASE_PASSWORD is not present.")

}
