package com.example.kiran.dint_notifications

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Comprobación de las versiones del SDK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationUtils(this)
        }
    }

}
