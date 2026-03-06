package com.calleserpis.elfonavidenyo.di

import android.app.Application
import androidx.room.Room
import com.calleserpis.elfonavidenyo.data.local.AppDatabase
import com.calleserpis.elfonavidenyo.data.repository.ElfRepositoryImpl
import com.calleserpis.elfonavidenyo.domain.repository.ElfRepository
import com.calleserpis.elfonavidenyo.domain.use_cases.ElfAdventuresUseCases
import com.calleserpis.elfonavidenyo.domain.use_cases.GetElfAdventureByIdUseCase
import com.calleserpis.elfonavidenyo.domain.use_cases.GetElfAdventuresUseCase
import com.calleserpis.elfonavidenyo.domain.use_cases.GetFavouriteElfAdventuresUseCase
import com.calleserpis.elfonavidenyo.domain.use_cases.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideElfDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()


    }

    @Provides
    @Singleton
    fun provideElfRepository(db: AppDatabase): ElfRepository {
        return ElfRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideElfAdventuresUseCases(
        repository: ElfRepository
    ): ElfAdventuresUseCases {
        return ElfAdventuresUseCases(
            getElfAdventures = GetElfAdventuresUseCase(repository),
            getElfAdventureById = GetElfAdventureByIdUseCase(repository),
            toggleFavorite = ToggleFavoriteUseCase(repository),
            getFavouriteElfAdventures = GetFavouriteElfAdventuresUseCase(repository)
        )

    }

}