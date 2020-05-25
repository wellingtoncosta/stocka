package io.github.wellingtoncosta.todoapp.backend.domain

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PrimitiveDescriptor
import kotlinx.serialization.PrimitiveKind
import kotlinx.serialization.Serializable

@Serializable(with = TodoStatus.Serializer::class)
enum class TodoStatus {
    PLANNED,
    IN_PROGRESS,
    DONE;

    companion object {
        fun of(value: String): TodoStatus {
            return values().firstOrNull { it.name == value }
                ?: throw InvalidTodoStatusException(value)
        }
    }

    internal object Serializer : KSerializer<TodoStatus> {

        override val descriptor = PrimitiveDescriptor(
            serialName = "TodoStatus",
            kind = PrimitiveKind.STRING
        )

        override fun deserialize(decoder: Decoder): TodoStatus {
            return when (val decoded = decoder.decodeString()) {
                "PLANNED" -> PLANNED
                "IN_PROGRESS" -> IN_PROGRESS
                "DONE" -> DONE
                else -> throw InvalidTodoStatusException(decoded)
            }
        }

        override fun serialize(encoder: Encoder, value: TodoStatus) {
            encoder.encodeString(value.toString())
        }

    }

}
