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
import java.io.Serializable
import kotlin.math.log

class MainActivity : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener{

    private val SIGN_IN = 9001
    private var mGoogleApiClient :GoogleApiClient? = null
    private  var islogin = false

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this,"ConnectionFailed!!",Toast.LENGTH_LONG).show()
        Log.e("ConnectionFailed",p0.toString())
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ss:String =""
        if (islogin){
            ss= intent.getStringExtra("logout")
        }


        uiUtil(islogin)
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

        if (islogin&&ss.equals("logout")){
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    object : ResultCallback<Status> {
                        override fun onResult(p0: Status) {
                            uiUtil(!p0.isSuccess)
                            Toast.makeText(this@MainActivity,"LogoutSuccessful",Toast.LENGTH_LONG).show()
                        }

                    }
            )
        }

    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//***Change Here***
        startActivity(intent)
        finish()
        System.exit(0)
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==SIGN_IN){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            Toast.makeText(this,"Signing in...",Toast.LENGTH_LONG).show()
            islogin = result.isSuccess
            uiUtil(islogin)
        }
    }

    private fun uiUtil(isLogin : Boolean){
        if (isLogin){
//            signin.visibility = View.GONE
//            logout.visibility = View.VISIBLE
            var intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
        else{
            signin.visibility = View.VISIBLE
//            logout.visibility = View.GONE
        }
    }
}






















