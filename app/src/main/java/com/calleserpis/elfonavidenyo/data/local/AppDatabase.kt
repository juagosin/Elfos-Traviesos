package com.calleserpis.elfonavidenyo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(ElfAdventureTypeConverters::class)
@Database(entities = [ElfAdventureEntity::class], version = 4, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: ElfAdventureDao
    companion object{
        const val DATABASE_NAME = "elf_adventures_db"
    }
}
