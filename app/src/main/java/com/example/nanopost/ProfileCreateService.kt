package com.example.nanopost

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/*
@AndroidEntryPoint
class ProfileCreateService : Service(), CoroutineScope by MainScope() {

    private val notificationManager by lazy {NotificationManagerCompat.from(this)}

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent? /*объект с инфой*/, flags: Int, startId: Int): Int {
        if (intent?.action == "$packageName$ACTION_CREATE_PROFILE"){
            val username = intent.getStringExtra("$packageName$EXTRA_USERNAME") ?: return super.onStartCommand(intent, flags, startId)
            startForeground(NOTIFICATION_ID, createNotification())
            val displayName = intent.getStringExtra("$packageName$EXTRA_DISPLAY_NAME")
            val bio = intent.getStringExtra("$packageName$EXTRA_BIO")

            launch {
                delay(10_000L)
                ServiceCompat.stopForeground(
                    this@ProfileCreateService,
                    ServiceCompat.STOP_FOREGROUND_REMOVE
                )
                stopSelf()
            }

            return START_STICKY
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }

    private fun createNotification(): Notification {

        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_add_24)
            .setContentTitle("хых")
            .setProgress(0, 0, true)
            .build()
    }

    fun ensureNotificationChannel(){
        val channel = NotificationChannelCompat.Builder(
            NOTIFICATION_CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_LOW
        ).build()
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "date_upload"
        private const val NOTIFICATION_ID = 1

        const val ACTION_CREATE_PROFILE = ".ACTION_CREATE_PROFILE"

        const val EXTRA_USERNAME = ".EXTRA_USERNAME"
        const val EXTRA_DISPLAY_NAME = ".EXTRA_DISPLAY_NAME"
        const val EXTRA_BIO = ".EXTRA_BIO"

        fun newIntent(
            context: Context,
            username: String,
            displayName:String?,
            bio: String?,
        ) = Intent(
            context.applicationContext,
            ProfileCreateService::class.java
        ).apply{
            action = ACTION_CREATE_PROFILE
            putExtra(EXTRA_USERNAME, username)
            putExtra(EXTRA_DISPLAY_NAME, displayName)
            putExtra(EXTRA_BIO, bio)
        }
    }

}*/