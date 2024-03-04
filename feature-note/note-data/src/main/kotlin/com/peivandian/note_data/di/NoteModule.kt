package com.peivandian.note_data.di

import com.peivandian.note_data.dataSources.NoteDataBase
import com.peivandian.note_data.dataSources.NoteDao
import com.peivandian.note_data.dataSources.NoteLocalDataSource
import com.peivandian.note_data.dataSources.NoteLocalDataSourceImpl
import com.peivandian.note_data.repositoriesImp.NoteRepositoryImpl
import com.peivandian.note_domain.repositories.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal abstract class NoteModule {

    @Binds
    internal abstract fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository

    @Binds
    internal abstract fun provideNotesLocal(noteLocalDataSourceImp: NoteLocalDataSourceImpl) : NoteLocalDataSource

    companion object {
        @Provides
        internal fun provideNotesDao(noteDatabase: NoteDataBase): NoteDao = noteDatabase.noteDao()

    }
}