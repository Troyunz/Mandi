package com.example.Mandi.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.Mandi.model.form

@Dao
interface FDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addJclass(items: form)

    @Query("SELECT * FROM details ORDER BY id DESC LIMIT 1")
    fun getJclass() : LiveData<form>

    @Query("DELETE FROM details")
    fun deleteAll()
}