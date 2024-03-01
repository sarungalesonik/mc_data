package com.example.myworkmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MyWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    private val CHANNEL_ID = "sample_service_channel"
    private val CHANNEL_NAME = "Sample Service Channel"
    private val context: Context = context.applicationContext

    override suspend fun doWork(): Result {
        // Simulate work by delaying for 1 minute
        delay(10000) // 1 minute = 60,000 milliseconds

        // Print the message
        Log.d("MyWorker", "Hello Vivek")

        // Show notification after work is done
        showNotification()

        return Result.success()
    }

    private fun showNotification() {
        // Create an intent to open the app when the notification is clicked
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Create a notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Sample worker")
            .setContentText("work completed")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Create a notification channel for Android Oreo and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        try {
            // Show the notification
            with(NotificationManagerCompat.from(context)) {
                notify(1, builder.build())
            }
        } catch (e: SecurityException) {
            // Handle SecurityException (e.g., log the error, show a message to the user)
            e.printStackTrace()
            Toast.makeText(context, "SecurityException occurred", Toast.LENGTH_SHORT).show()
        }
    }
}
