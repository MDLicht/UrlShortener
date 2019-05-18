package com.mdlicht.zb.simpleurlshortener.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Result(
    val hash: String?,
    val orgUrl: String?,
    val url: String?,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0
)