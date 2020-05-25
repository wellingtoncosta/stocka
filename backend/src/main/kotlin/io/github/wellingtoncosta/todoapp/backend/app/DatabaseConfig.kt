package io.github.wellingtoncosta.todoapp.backend.app

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

private fun buildDataSource() = HikariDataSource(HikariConfig().apply {
    driverClassName = "org.postgresql.Driver"
    jdbcUrl =
        EnvironmentVariables.jdbcDatabaseUrl
    username =
        EnvironmentVariables.jdbcDatabaseUsername
    password =
        EnvironmentVariables.jdbcDatabasePassword
})

private fun runMigrations() {
    Flyway.configure().run {
        dataSource(buildDataSource()).load()
    }.apply { migrate() }
}

private fun connectToDatabase() {
    Database.connect(buildDataSource())
}

fun setupDatabase() {
    runMigrations()
    connectToDatabase()
}
