package com.calleserpis.elfonavidenyo.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ElfAdventureDao {
    @Query("SELECT * FROM elf_adventures")
    fun getElfAdventures(): Flow<List<ElfAdventureEntity>>

    @Query("SELECT * FROM elf_adventures WHERE id = :id")
    fun getElfAdventureById(id: Int?): Flow<ElfAdventureEntity?>

    @Query("SELECT * FROM elf_adventures WHERE isFavorite = 1")
    fun getFavouriteElfAdventures(): Flow<List<ElfAdventureEntity>>

    @Query("UPDATE elf_adventures SET isFavorite = NOT isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: Int)

}