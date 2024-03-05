package com.peivandian.note_ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.CountDownLatch

@HiltWorker
class SynchronizationWorker @AssistedInject constructor(
    private var notificationBuilder: NotificationCompat.Builder,
    private var notificationManager: NotificationManagerCompat,
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters
) : Worker(context, workerParams) {
    private val countDownLatch = CountDownLatch(1)

    override fun doWork(): Result {
//        val notificationHelper = NotificationHelper(context)
        showSimpleNotification(context)
        var workResult: Result = Result.success()
        //doThings
        return workResult
    }

    @SuppressLint("MissingPermission")
    fun showSimpleNotification(context: Context) {

//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//
        notificationManager.notify(1, notificationBuilder.build())
    }
}
