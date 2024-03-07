package com.peivandian.note_ui.util.workManager

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class RemindViewModel @Inject constructor(
    private var workManager: WorkManager
): ViewModel() {

    @SuppressLint("SuspiciousIndentation")
    fun scheduleReminder(
        duration: Long,
        timeUnit: TimeUnit,
        title: String,
        description: String
    ) {

        // create a Data instance with the plantName passed to it
        val myWorkRequestBuilder = OneTimeWorkRequestBuilder<NotificationWorker>()
                myWorkRequestBuilder.setInputData(
                    workDataOf(
                        "NAME" to title,
                        "MESSAGE" to description
                    )
                )

        myWorkRequestBuilder.setInitialDelay(duration, timeUnit)
        workManager.enqueue(myWorkRequestBuilder.build())
    }

}