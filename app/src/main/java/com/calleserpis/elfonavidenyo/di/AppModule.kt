package com.calleserpis.elfonavidenyo.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

        // ─── Helper reutilizable ───────────────────────────────────────
        fun executeSqlFile(db: SupportSQLiteDatabase, fileName: String) {
            val sql = app.assets.open("database/$fileName")
                .bufferedReader()
                .readText()

            sql.split(Regex("(?i)(?=INSERT OR IGNORE|INSERT INTO|CREATE TABLE|DROP TABLE)"))
                .map { it.trim() }
                .filter { it.isNotBlank() && !it.startsWith("--") }
                .forEachIndexed { index, statement ->
                    try {
                        val clean = statement.trimEnd(';', ' ', '\n')
                        if (clean.isNotBlank()) {
                            db.execSQL(clean)
                            Log.d("DB_SEED", "✅ [$fileName] Statement $index OK")
                        }
                    } catch (e: Exception) {
                        Log.e("DB_SEED", "❌ [$fileName] Error en statement $index: ${e.message}")
                    }
                }
        }

        // ─── Migrations ────────────────────────────────────────────────
//        val migration1To2 = object : Migration(1, 2) {
//            override fun migrate(db: SupportSQLiteDatabase) {
//                Log.d("DB_SEED", "🔄 Ejecutando migration 1 → 2")
//                executeSqlFile(db, "migration_v2.sql")
//            }
//        }

        // ─── Callback (primera instalación) ───────────────────────────
        val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d("DB_SEED", "🚀 onCreate - primera instalación")
                executeSqlFile(db, "seed.sql")
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.d("DB_SEED", "📂 onOpen - BD ya existía")


            }
        }

        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            //.addMigrations(migration1To2)       // ← añade una línea por cada migration
            .fallbackToDestructiveMigration(true)
            .addCallback(callback)  // ← en lugar de createFromAsset
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