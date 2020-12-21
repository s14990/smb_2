package com.example.s14990_smb_proj2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationService : Service() {
    private var id=0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val context=this.applicationContext
        createChannel(context)
        id++
        val item_id= intent?.getLongExtra("_ID","0".toLong())
        val item_name= intent?.getStringExtra("Name")
        val i2 = Intent("com.example.s14990.ITEM_EDIT")
        i2.component = ComponentName("com.example.s14990_smb_proj1",
                "com.example.s14990_smb_proj1.EditItemActivity")
        i2.putExtra("_ID",item_id)

        val pI= PendingIntent.getActivity(context,id,i2, PendingIntent.FLAG_ONE_SHOT)

        val n= NotificationCompat.Builder(context, context.getString(R.string.channelID))
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Nowy Item")
            .setContentText("${item_id.toString()} $item_name")
            .setAutoCancel(true)
            .setContentIntent(pI)
            .build()

        NotificationManagerCompat.from(context).notify(id,n)
        return super.onStartCommand(intent, flags, startId)
    }


    private fun createChannel(context: Context) {
        val c = NotificationChannel(
            context.getString(R.string.channelID),
            context.getString(R.string.channelName),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        NotificationManagerCompat.from(context).createNotificationChannel(c)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

}