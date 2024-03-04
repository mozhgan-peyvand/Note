package com.peivandian.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peivandian.note_data.dataSources.NoteDataBase
import com.peivandian.note_models.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() , NoteDataBase