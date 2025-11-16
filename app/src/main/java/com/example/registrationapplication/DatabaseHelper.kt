package com.example.registrationapplication
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper (context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "users_db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USERS = "users"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_DOB = "dob"
        private const val COL_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COL_NAME TEXT, "
                + "$COL_DOB TEXT, "
                + "$COL_EMAIL TEXT);")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun insertUser(name: String, dob: String, email: String): Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, name)
        cv.put(COL_DOB, dob)
        cv.put(COL_EMAIL, email)
        val id = db.insert(TABLE_USERS, null, cv)
        db.close()
        return id
    }

    fun getAllUsers(): ArrayList<User> {
        val list = ArrayList<User>()
        val select = "SELECT * FROM $TABLE_USERS ORDER BY $COL_ID DESC"
        val db = this.readableDatabase
        val cursor = db.rawQuery(select, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME))
                val dob = cursor.getString(cursor.getColumnIndexOrThrow(COL_DOB))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL))
                list.add(User(name, dob, email))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

}