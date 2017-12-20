package com.example.yolo.basic_kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener{

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this,"ConnectionFailed!!",Toast.LENGTH_LONG)
        Log.e("ConnectionFailed",p0.toString())
    }

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}




















