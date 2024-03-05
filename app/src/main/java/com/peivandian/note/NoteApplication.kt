package com.peivandian.note

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NoteApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }


//    @Inject
//    lateinit var workerFactory: HiltWorkerFactory
//
//    fun getWorkManagerConfiguration() = Configuration.Builder()
//        .setWorkerFactory(workerFactory)
//        .build()
}