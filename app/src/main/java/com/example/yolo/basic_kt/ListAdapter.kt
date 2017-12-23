package com.example.yolo.basic_kt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.string
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listview.*
import kotlinx.android.synthetic.main.listview.view.*

/**
 * Created by Swati Bothra on 22/12/17.
 */

public class ListAdapter(context:Context,array:JsonArray<JsonObject>): BaseAdapter() {

    private val inflator: LayoutInflater
    private val array :JsonArray<JsonObject>
    private val context : Context

    init {
        this.context=context
        this.inflator= LayoutInflater.from(context)
        this.array = array
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun  getCount(): Int {
        return array.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var con: View? = convertView as  View?
        con = inflator.inflate(R.layout.listview,parent,false)
        con.title_listview.setText(array[position].string("title"))
        con.desc_listview.setText(array[position].string("description"))
        con.author_listview.setText(array[position].string("author"))
        con.publish_date_listview.setText(array[position].string("publishedAt"))
        Picasso.with(context).load(array[position].string("urlToImage")).into(con.img_listview)
        return con
    }

}