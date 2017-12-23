package com.example.yolo.basic_kt

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.beust.klaxon.*
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import kotlinx.android.synthetic.main.second_activity.*
import java.io.Serializable

/**
 * Created by Swati Bothra on 18/12/17.
 */
class SecondActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        var result = ""



        doAsync {
            result = URL("https://newsapi.org/v2/top-headlines?sources=bbc-news&sortBy=popularity&apiKey=b510575e772d4de494b282537828f819").readText()
            val parser :Parser = Parser()
            val stringBuilder :StringBuilder = StringBuilder(result)
            val json :JsonObject = parser.parse(stringBuilder) as JsonObject
            val array :JsonArray<JsonObject> = json.array<JsonObject>("articles") as JsonArray<JsonObject>

            uiThread {
                listview.adapter = ListAdapter(this@SecondActivity,array)
            }
        }

        logout.setOnClickListener(View.OnClickListener {
            var intent :Intent = Intent(this,MainActivity::class.java)
            intent.putExtra("logout","logout")
            startActivity(intent)
        })
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
}

//NEWS API KEY :- b510575e772d4de494b282537828f819