package com.peivandian.note_ui.util.workManager

import android.annotation.SuppressLint
import android.content.Context
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
        notificationManager.notify(17, notificationBuilder.build())
        return Result.success()
    }

}
