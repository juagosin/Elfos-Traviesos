package com.calleserpis.elfontheshelf.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ElfAdventureDao {
    @Query("SELECT * FROM elf_adventures")
    fun getElfAdventures(): Flow<List<ElfAdventureEntity>>

}