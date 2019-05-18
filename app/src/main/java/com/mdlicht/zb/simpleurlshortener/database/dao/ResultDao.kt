package com.mdlicht.zb.simpleurlshortener.database.dao

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.mdlicht.zb.simpleurlshortener.model.Result

@Dao
interface ResultDao {
    @Query("SELECT * FROM result")
    fun getAllHistory(): LiveData<List<Result>>

    @Query("SELECT * FROM result ORDER BY uid DESC")
    fun getPagedHistory(): DataSource.Factory<Int, Result>

    @Insert
    fun insertHistory(result: Result)

    @Delete
    fun deleteHistory(result: Result)

    @Update
    fun updateHistory(result: Result)
}