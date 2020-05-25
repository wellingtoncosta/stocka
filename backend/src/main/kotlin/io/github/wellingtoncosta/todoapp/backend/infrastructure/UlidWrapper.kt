package io.github.wellingtoncosta.todoapp.backend.infrastructure

import de.huxhorn.sulky.ulid.ULID

object UlidWrapper {

    const val ULID_LENGTH = 26

    private val instance = ULID()

    fun getNext(): String = instance.nextULID()

}