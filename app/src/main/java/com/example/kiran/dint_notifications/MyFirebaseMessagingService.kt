package com.example.kiran.dint_notifications

import android.annotation.SuppressLint
import android.app.Notification
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "FirebaseMessaging"

    //Contenido del mensaje
    @SuppressLint("LongLogTag")
    override fun onNewToken(token: String?) {
        Log.d(TAG, "Message received: ${token}")
    }

    //Lugar de procedencia de la notifiación y donde se muestra en el móvil/emulador
    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "Message received: ${remoteMessage.from}")

        if (remoteMessage.notification != null) {
            showNotification(remoteMessage)
        }
    }

    //Se muestra la notificación
    private fun showNotification(remoteMessage: RemoteMessage) {
        val mNotificationID = 101
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(mNotificationID, notificationIntent(remoteMessage))
    }

    //Valor por defecto de la notificación
    private fun defaultNotification(remoteMessage: RemoteMessage) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Notification.Builder(this, NotificationUtils.CHANNEL_ID)
    }
    else {
        Notification.Builder(this)
    }.apply {
        //Se cogen los datos de la notificación y se setean en la notificación que se muestra
        setContentTitle(remoteMessage.notification?.title)
        setContentText(remoteMessage.notification?.body)
        setSmallIcon(android.R.drawable.ic_dialog_info)
    }

    //Lugar en el que se muestra la notificación
    private fun notificationIntent(remoteMessage: RemoteMessage) = PendingIntent.getActivity(this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT).run {
        defaultNotification(remoteMessage).setContentIntent(this).build()
    }
}