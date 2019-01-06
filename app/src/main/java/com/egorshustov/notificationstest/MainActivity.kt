package com.egorshustov.notificationstest

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
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
    private lateinit var mediaSessionCompat: MediaSessionCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManagerCompat = NotificationManagerCompat.from(this)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextMessage = findViewById(R.id.edit_text_message)
        mediaSessionCompat = MediaSessionCompat(this, "tag")
    }

    fun sendOnChannel1(v: View) {
        val title = editTextTitle.text.toString()
        val message = editTextMessage.text.toString()

        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this,
            0, activityIntent, 0)

        val picture = BitmapFactory.decodeResource(resources, R.drawable.me)

        val notification = NotificationCompat.Builder(this, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(picture)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(picture)
                    .bigLargeIcon(null)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)  // for APIs lower than 26
            .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))  // for APIs lower than 26
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(contentIntent)
            .build()

        notificationManagerCompat.notify(1, notification)
    }

    fun sendOnChannel2(v: View) {
        val title = editTextTitle.text.toString()
        val message = editTextMessage.text.toString()

        val artwork = BitmapFactory.decodeResource(resources, R.drawable.me)

        val notification = NotificationCompat.Builder(this, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_looks_two_black_24dp)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(artwork)
            .addAction(R.drawable.ic_not_interested_black_24dp, "Dislike", null)
            .addAction(R.drawable.ic_skip_previous_black_24dp, "Previous", null)
            .addAction(R.drawable.ic_pause_black_24dp, "Pause", null)
            .addAction(R.drawable.ic_skip_next_black_24dp, "Next", null)
            .addAction(R.drawable.ic_favorite_black_24dp, "Like", null)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(1, 2, 3)
                    .setMediaSession(mediaSessionCompat.sessionToken)
            )
            .setSubText("Sub Text")
            .setPriority(NotificationCompat.PRIORITY_LOW) // for APIs lower than 26
            .build()

        notificationManagerCompat.notify(2, notification)
    }
}
