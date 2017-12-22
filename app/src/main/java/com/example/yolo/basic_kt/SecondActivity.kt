package com.example.yolo.basic_kt

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

/**
 * Created by Swati Bothra on 18/12/17.
 */
class SecondActivity : Activity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        Log.e("SecondActivity","before URL")

        var result = ""

        doAsync {
            result = URL("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=b510575e772d4de494b282537828f819").readText()
            val parser :Parser = Parser()
            val stringBuilder :StringBuilder = StringBuilder(result)
            val json :JsonObject = parser.parse(stringBuilder) as JsonObject
            val array :JsonArray<JsonObject> = json.array<JsonObject>("articles") as JsonArray<JsonObject>

            val author :String = array[0].string("author") as String
            val title :String = array[0].string("title")as String
            val desc :String = array[0].string("description")as String
            val imgUrl :String = array[0].string("urlToImage")as String
            val publishDate :String = array[0].string("publishedAt")as String

            uiThread {
                Log.e("seconActivity",result)
                Log.e("SecondActivity","after URL")
                Log.e("SecondActivity",author)
                Log.e("SecondActivity",title)
                Log.e("SecondActivity",desc)
                Log.e("SecondActivity",imgUrl)
                Log.e("SecondActivity",publishDate)
            }
        }




    }

}

//NEWS API KEY :- b510575e772d4de494b282537828f819