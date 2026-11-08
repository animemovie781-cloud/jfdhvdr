package com.kinecut.app.export

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ExportForegroundService : Service() {

    companion object {
        const val CHANNEL_ID = "kinecut_export"
        const val NOTIFICATION_ID = 1001
        const val ACTION_START = "com.kinecut.app.export.START"
        const val ACTION_STOP = "com.kinecut.app.export.STOP"
        const val EXTRA_PROJECT_ID = "project_id"

        fun start(context: Context, projectId: String) {
            val intent = Intent(context, ExportForegroundService::class.java).apply {
                action = ACTION_START
                putExtra(EXTRA_PROJECT_ID, projectId)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> {
                startForeground(NOTIFICATION_ID, createNotification(0))
                val projectId = intent.getStringExtra(EXTRA_PROJECT_ID) ?: ""
                startExport(projectId)
            }
            ACTION_STOP -> {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun startExport(projectId: String) {
        // Export logic delegated to ExportEngine
    }

    fun updateProgress(progress: Int) {
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID, createNotification(progress))
    }

    private fun createNotification(progress: Int): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Exporting video…")
            .setContentText("$progress% complete")
            .setSmallIcon(android.R.drawable.ic_menu_save)
            .setProgress(100, progress, progress == 0)
            .setOngoing(true)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Video Export",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}
