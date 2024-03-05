package com.peivandian.note_ui.util.ui

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.peivandian.note_ui.R
import javax.inject.Inject

class ReminderWorker @Inject constructor(
    context: Context,
    workerParams: WorkerParameters,
    private val notificationManager: NotificationManagerCompat

) : Worker(context, workerParams) {

    // Arbitrary id number
    private val notificationId = 17

    @SuppressLint("MissingPermission")
    override fun doWork(): Result {
//        val intent = Intent(applicationContext, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }

//        val intent = Intent(applicationContext, MyReceiver::class.java).apply {
//            putExtra("MESSAGE", "Clicked!")
//        }
//        val pendingIntent: PendingIntent = PendingIntent.getActivity(
//            applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
//        )
//
//        val plantName = inputData.getString(nameKey)
//
//        val body = "Hello, It's time to water your $plantName and spray pesticides to avoid powdery mildew."
//        val builder = NotificationCompat.Builder(applicationContext, "reminder_id")
//            .setSmallIcon(R.drawable.edit_2)
//            .setContentTitle("Reminder App.")
//            .setContentText(body)
//            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
////            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//
//        with(NotificationManagerCompat.from(applicationContext)) {
//            notify(notificationId, builder.build())
//        }

        return Result.success()
    }

    companion object {
        const val nameKey = "NAME"
    }
}