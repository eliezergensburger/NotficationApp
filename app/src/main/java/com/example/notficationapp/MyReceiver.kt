package com.example.notficationapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService


class MyReceiver : BroadcastReceiver() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        // Extract data included in the Intent
        if (intent.action!!.matches(Regex("com.example.travelbrokerage.CUSTOM_ACTION"))) {

            notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(context, channelId)
                    .setSmallIcon(R.drawable.ic_new_travel)
                    .setContentTitle("תיווך נסיעות")
                    .setContentText("נוספה נסיעה חדשה")
            } else {

                builder = Notification.Builder(context)
                    .setContentTitle("תיווך נסיעות")
                    .setContentText("נוספה נסיעה חדשה")
                    .setSmallIcon(R.drawable.ic_new_travel)
            }
            notificationManager.notify(1234, builder.build())
        }
    }
}