package com.example.yolo.basic_kt

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import kotlinx.android.synthetic.main.second_activity.*
/**
 * Created by Swati Bothra on 18/12/17.
 */
class SecondActivity : Activity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        Log.e("SecondActivity","before URL")

        var result = ""
//        var author = ""
//        var title = ""
//        var desc = ""
//        var publishDate =""
//        var imgUrl = ""

        doAsync {
            result = URL("https://newsapi.org/v2/everything?q=apple&from=2017-12-22&to=2017-12-22&sortBy=popularity&apiKey=b510575e772d4de494b282537828f819").readText()
            val parser :Parser = Parser()
            val stringBuilder :StringBuilder = StringBuilder(result)
            val json :JsonObject = parser.parse(stringBuilder) as JsonObject
            val array :JsonArray<JsonObject> = json.array<JsonObject>("articles") as JsonArray<JsonObject>

//            author = array[0].string("author") as String
//            title = array[0].string("title")as String
//            desc = array[0].string("description")as String
//            imgUrl = array[0].string("urlToImage")as String
//            publishDate = array[0].string("publishedAt")as String

            uiThread {
                Log.e("seconActivity",result)
                Log.e("SecondActivity","after URL")
                listview.adapter = ListAdapter(this@SecondActivity,array)

            }
        }








    }

}

//NEWS API KEY :- b510575e772d4de494b282537828f819