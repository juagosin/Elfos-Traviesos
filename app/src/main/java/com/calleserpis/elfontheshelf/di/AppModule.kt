package com.calleserpis.elfontheshelf.di

import android.app.Application
import androidx.room.Room
import com.calleserpis.elfontheshelf.data.local.AppDatabase
import com.calleserpis.elfontheshelf.data.repository.ElfRepositoryImpl
import com.calleserpis.elfontheshelf.domain.repository.ElfRepository
import com.calleserpis.elfontheshelf.domain.use_cases.ElfAdventuresUseCases
import com.calleserpis.elfontheshelf.domain.use_cases.GetElfAdventuresUseCase
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
        ).build()


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
            getElfAdventures = GetElfAdventuresUseCase(repository)
        )

    }

}