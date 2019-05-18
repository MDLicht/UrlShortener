package com.mdlicht.zb.simpleurlshortener.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mdlicht.zb.simpleurlshortener.database.dao.ResultDao
import com.mdlicht.zb.simpleurlshortener.model.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun resultDao(): ResultDao
}