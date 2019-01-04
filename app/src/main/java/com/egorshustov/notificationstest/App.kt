package com.egorshustov.notificationstest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                CHANNEL_1_ID,
                "Name of Channel 1",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel1.enableLights(true)
            channel1.lightColor = Color.RED
            channel1.enableVibration(true)
            channel1.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            channel1.description = "Description of Channel 1"

            val channel2 = NotificationChannel(
                CHANNEL_2_ID,
                "Name of Channel 2",
                NotificationManager.IMPORTANCE_LOW
            )
            channel2.enableLights(true)
            channel2.lightColor = Color.BLUE
            channel2.description = "Description of Channel 2"

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel1)
            notificationManager.createNotificationChannel(channel2)
        }
    }

    companion object {
        const val CHANNEL_1_ID = "channel1"
        const val CHANNEL_2_ID = "channel2"
    }
}