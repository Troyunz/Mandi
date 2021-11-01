package com.example.Mandi.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.Mandi.model.OtherMandi


@Dao
interface MDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMitems(otherMandi: List<OtherMandi>)

    @Query("SELECT * FROM othermandi")
    fun getmitems(): LiveData<List<OtherMandi>>
}