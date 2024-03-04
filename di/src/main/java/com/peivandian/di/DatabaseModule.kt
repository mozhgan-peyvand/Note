package com.peivandian.di

import android.content.Context
import androidx.room.Room
import com.peivandian.note_data.dataSources.NoteDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal abstract class DatabaseModule {

    companion object {
        @Provides
        @Singleton
        internal fun provideRoom(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "note"
            ).build()
        }
    }

    @Binds
    @Singleton
    internal abstract fun bindCarsDataBase(appDatabase: AppDatabase): NoteDataBase
}