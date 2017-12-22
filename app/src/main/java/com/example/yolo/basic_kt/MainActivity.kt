package com.example.yolo.basic_kt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener{

    private val SIGN_IN = 9001
    private var mGoogleApiClient :GoogleApiClient? = null

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this,"ConnectionFailed!!",Toast.LENGTH_LONG).show()
        Log.e("ConnectionFailed",p0.toString())
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uiUtil(false)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build()

        signin.setOnClickListener(View.OnClickListener {
            var signinIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signinIntent,SIGN_IN)
        })

//        logout.setOnClickListener(View.OnClickListener {
//            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                    object : ResultCallback<Status> {
//                        override fun onResult(p0: Status) {
//                            uiUtil(!p0.isSuccess)
//                            Toast.makeText(this@MainActivity,"LogoutSuccessful",Toast.LENGTH_LONG).show()
//                        }
//
//                    }
//            )
//        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==SIGN_IN){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            Toast.makeText(this,"SignInSuccessful",Toast.LENGTH_LONG).show()
            uiUtil(result.isSuccess)
        }
    }

    private fun uiUtil(isLogin : Boolean){
        if (isLogin){
            Log.e("sigin","signinf innn")
//            signin.visibility = View.GONE
//            logout.visibility = View.VISIBLE
            var intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
            Log.e("intent","intenttttttttttt")
        }
        else{
            Log.e("logout","logotitu")
            signin.visibility = View.VISIBLE
//            logout.visibility = View.GONE
        }
    }
}




















