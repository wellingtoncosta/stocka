package io.github.wellingtoncosta.todoapp.domain

import kotlinx.serialization.*

@Serializable(with = TodoStatus.Serializer::class)
enum class TodoStatus {
    PLANNED,
    IN_PROGRESS,
    DONE;

    fun capitalized(): String {
        val status = toString().toLowerCase()
        val firstLetter = status.substring(0, 1).toUpperCase()
        return "$firstLetter${status.substring(1)}"
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
                else -> throw IllegalArgumentException("Unable to decode $decoded to TodoStatus.")
            }
        }

        override fun serialize(encoder: Encoder, value: TodoStatus) {
            encoder.encodeString(value.toString())
        }

    }


}
