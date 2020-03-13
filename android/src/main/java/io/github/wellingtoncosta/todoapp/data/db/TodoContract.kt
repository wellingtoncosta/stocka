package io.github.wellingtoncosta.todoapp.data.db

import android.provider.BaseColumns

object TodoContract {

    object TodoEntry : BaseColumns {
        const val TABLE_NAME = "todo"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DETAILS = "details"
        const val COLUMN_NAME_STATUS = "status"
    }

    object FeedQueries {
        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${TodoEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${TodoEntry.COLUMN_NAME_TITLE} TEXT NOT NULL," +
                    "${TodoEntry.COLUMN_NAME_DETAILS} TEXT," +
                    "${TodoEntry.COLUMN_NAME_STATUS} INTEGER);"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TodoEntry.TABLE_NAME};"
    }

}
