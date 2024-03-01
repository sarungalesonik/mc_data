package com.example.practice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Sample : Service() {

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    // Notification channel ID and name
    private val CHANNEL_ID = "sample_service_channel"
    private val CHANNEL_NAME = "Sample Service Channel"

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                return
            }
            stopSelf(msg.arg1)
            // Notify user when service is completed
            showNotification()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val handlerThread = HandlerThread("simpleThread", android.os.Process.THREAD_PRIORITY_BACKGROUND)
        handlerThread.start()
        serviceLooper = handlerThread.looper
        serviceHandler = ServiceHandler(serviceLooper!!)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show()

        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show()
    }

    private fun showNotification() {
        // Create an intent to open the app when the notification is clicked
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Create a notification
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Sample Service")
            .setContentText("Service completed")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Create a notification channel for Android Oreo and higher
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        try {
            // Show the notification
            with(NotificationManagerCompat.from(this)) {
                notify(1, builder.build())
            }
        } catch (e: SecurityException) {
            // Handle SecurityException (e.g., log the error, show a message to the user)
            e.printStackTrace()
            Toast.makeText(this, "SecurityException occurred", Toast.LENGTH_SHORT).show()
        }
    }
}
