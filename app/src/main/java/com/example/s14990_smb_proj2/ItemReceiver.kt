package com.example.s14990_smb_proj2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.KeyEventDispatcher

class ItemReceiver : BroadcastReceiver() {



    override fun onReceive(context: Context, intent: Intent) {
        Log.i("Received action",intent.action.toString())
        if(intent.action == "com.example.s14990_smb_proj1.ADD_ITEM"){
            val item_id=intent.getLongExtra("_ID","0".toLong())
            val item_name=intent.getStringExtra("Name")

            val sIntent = Intent(context, NotificationService::class.java)
            sIntent.putExtra("_ID", item_id)
            sIntent.putExtra("Name", item_name)
            context.startService(sIntent)
        }
    }


}