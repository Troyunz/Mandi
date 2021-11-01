package com.example.Mandi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.Mandi.model.OtherMandi

@Database(entities = [OtherMandi::class], version = 1)
abstract class MDatabase: RoomDatabase() {
    abstract fun mDao():MDao

    companion object{
        @Volatile
        private var INSTANCE: MDatabase? = null

        fun getDatabase(context: Context): MDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MDatabase::class.java,
                        "mandiDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}