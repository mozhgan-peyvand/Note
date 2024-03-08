package com.peivandian.note_ui.util.workManager

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.CountDownLatch

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    private var notificationBuilder: NotificationCompat.Builder,
    private var notificationManager: NotificationManagerCompat,
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {

        val noteTitle = inputData.getString(RemindViewModel.NAME)
        val noteDescription = inputData.getString(RemindViewModel.MESSAGE)

            notificationManager.notify(
                17,
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .setBigContentTitle("$noteTitle")
                        .bigText("$noteDescription")
                )
                    .build()
            )

        return Result.success()
    }

}
