package com.egorshustov.notificationstest

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.egorshustov.notificationstest.App.Companion.CHANNEL_1_ID
import com.egorshustov.notificationstest.App.Companion.CHANNEL_2_ID

class MainActivity : AppCompatActivity() {
    private lateinit var notificationManagerCompat: NotificationManagerCompat
    private lateinit var editTextTitle: EditText
    private lateinit var editTextMessage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManagerCompat = NotificationManagerCompat.from(this)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextMessage = findViewById(R.id.edit_text_message)
    }

    fun sendOnChannel1(v: View) {
        val title = editTextTitle.text.toString()
        val message = editTextMessage.text.toString()

        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this,
            0, activityIntent, 0)

        val broadcastIntent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("com.egorshustov.notificationstest.TOAST_MESSAGE", message)
        val actionIntent = PendingIntent.getBroadcast(this,
            0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)  // for APIs lower than 26
            .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))  // for APIs lower than 26
            .setColor(Color.RED)  // for APIs lower than 26
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(contentIntent)
            .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
            .build()

        notificationManagerCompat.notify(1, notification)
    }

    fun sendOnChannel2(v: View) {
        val notification = NotificationCompat.Builder(this, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_looks_two_black_24dp)
            .setContentTitle(editTextTitle.text)
            .setContentText(editTextMessage.text)
            .setPriority(NotificationCompat.PRIORITY_LOW) // for APIs lower than 26
            .build()

        notificationManagerCompat.notify(2, notification)
    }
}
