package com.peivandian.note_ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class RemindViewModel @Inject constructor(
    @ApplicationContext private var context: Context,
): ViewModel() {




    @SuppressLint("SuspiciousIndentation")
    fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String,
    ) {

        // create a Data instance with the plantName passed to it
        val myWorkRequestBuilder = OneTimeWorkRequestBuilder<SynchronizationWorker>()
//        for (items in itemsList.toMutableList()) {
//            if (items.name == plantName) {
                myWorkRequestBuilder.setInputData(
                    workDataOf(
                        "NAME" to "items.name",
                        "MESSAGE" to "items.description"
                    )
                )
//            }
//        }
        myWorkRequestBuilder.setInitialDelay(duration, unit)
        WorkManager.getInstance(context).enqueue(myWorkRequestBuilder.build())
    }

}