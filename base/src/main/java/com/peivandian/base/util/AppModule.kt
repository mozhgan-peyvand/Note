package com.peivandian.base.util

import android.app.Application
import android.app.Notification.VISIBILITY_PRIVATE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
import androidx.work.WorkManager
import com.peivandian.base.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val MY_URI = "https://stevdza-san.com"
    const val MY_ARG = "message"

    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context:Context
    ): NotificationCompat.Builder {

        val routeIntent = Intent(
            Intent.ACTION_VIEW,
            "note://uinote://notelistscreen".toUri()
        ).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val flags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT

        val pending = PendingIntent.getActivity(
            context,
            0,
            routeIntent,
            flags
        )
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = "note://uinote://notelistscreen".toUri()
        }

//        val deepLinkPendingIntent = TaskStackBuilder.create(context).run {
//            addNextIntentWithParentStack(intent)
//            getPendingIntent(1234, FLAG_UPDATE_CURRENT)
//        }
//        val intent = Intent(context, MyReceiver::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

//        val plantName = inputData.getString(nameKey)

        val body = "Hello, It's time to water your  and spray pesticides to avoid powdery mildew."
        return NotificationCompat.Builder(context, "Main Channel ID")
            .setSmallIcon(R.drawable.calendar_2)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
//        val intent = Intent(context, MyReceiver::class.java).apply {
//            putExtra("MESSAGE", "Clicked!")
//        }
//        val flag =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//                PendingIntent.FLAG_IMMUTABLE
//            else
//                0
//        val pendingIntent = PendingIntent.getBroadcast(
//            context,
//            0,
//            intent,
//            flag
//        )
//
//        return NotificationCompat.Builder(context, "Main Channel ID")
//            .setContentTitle("Welcome")
//            .setContentText("YouTube Channel: Stevdza-San")
//            .setSmallIcon(R.drawable.calendar_2)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
//            .setPublicVersion(
//                NotificationCompat.Builder(context, "Main Channel ID")
//                    .setContentTitle("Hidden")
//                    .setContentText("Unlock to see the message.")
//                    .build()
//            )
//            .addAction(0, "ACTION", pendingIntent)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat{
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Main Channel ID",
                "Main Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)
}