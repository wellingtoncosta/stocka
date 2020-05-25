package io.github.wellingtoncosta.todoapp.backend.infrastructure

import io.github.wellingtoncosta.todoapp.backend.domain.TodoStatus
import io.github.wellingtoncosta.todoapp.backend.infrastructure.UlidWrapper.ULID_LENGTH
import org.jetbrains.exposed.sql.Table

const val VARCHAR_MAX = 255

object TodoTable : Table("todo") {

    val id = varchar(name = "id", length = ULID_LENGTH)
    val title = varchar("title", length = VARCHAR_MAX)
    val description = varchar(name = "description", length = VARCHAR_MAX).nullable()
    val status = enumerationByName(name = "status", length = VARCHAR_MAX, klass = TodoStatus::class)

}
