package com.example.kiran.dint_notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class NotificationUtils(base: Context): ContextWrapper(base) {
    //Ubicación del archivo para el canal de la versión utilizada
    companion object {
        const val CHANNEL_ID = "com.example.kiran.notifications.CHANNEL_ID"
        const val CHANNEL_NAME = "Show notifications"
    }

    private val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createChannels()
    }

    //Función para borrar las notificaciones
    fun deleteNotificationChannel() {
        mNotificationManager.deleteNotificationChannel(CHANNEL_ID)
    }

    //Función para crear las notificaciones
    private fun createChannels() {
        //Todos los datos relevantes de las notificaciones
        NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
            enableLights(true)
            enableVibration(true)
            importance = NotificationManager.IMPORTANCE_LOW
            lightColor = Color.GREEN
            lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            //Creación del canal de la notificación
            mNotificationManager.createNotificationChannel(this)
        }
    }
}