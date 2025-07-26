package com.example.masshcet.data

import androidx.room.Database
import androidx.room.RoomDatabase


//@TypeConverters(DateTypeConverter::class) // Add the converter here
@Database(entities = [MemberEntity::class], version = 1, exportSchema = false)
abstract class MemberDatabase:RoomDatabase() {
    abstract fun memberDao():MemberDao
}