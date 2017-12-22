package com.example.yolo.basic_kt

import android.app.Activity
import android.os.Bundle
import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by yolo on 18/12/17.
 */
class SecondActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        Log.e("SecondActivity","before URL")


        doAsync {
            val result = URL("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=b510575e772d4de494b282537828f819").readText()
            uiThread {
                Log.e("seconActivity",result)
                Log.e("SecondActivity","after URL")
            }
        }



    }

}

//API KEY :- b510575e772d4de494b282537828f819