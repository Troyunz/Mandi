package com.example.Mandi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.Mandi.model.form

@Database(entities = [form::class], version = 1)
//@TypeConverters(Converters::class)
abstract class fDatabase : RoomDatabase() {

    abstract fun fDao():FDao

    companion object{
        @Volatile
        private var INSTANCE: fDatabase? = null

        fun getDatabase(context: Context): fDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        fDatabase::class.java,
                        "quoteDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}